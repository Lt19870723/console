package com.cxdai.console.cgnotify.service;

import com.cxdai.console.account.cash.mapper.CashRecordMapper;
import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.cgnotify.entity.*;
import com.cxdai.console.cgnotify.mapper.*;
import com.cxdai.console.cgnotify.vo.EReconHeaderVo;
import com.cxdai.console.cgnotify.vo.EReconResultVo;
import com.cxdai.console.cgnotify.vo.ReconCnd;
import com.cxdai.console.cgnotify.vo.ReconResultCnd;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.mapper.AccountLogMapper;
import com.cxdai.console.statistics.account.vo.AccountLogCnd;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.SFTPUtil;
import com.google.common.io.Files;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * <p>
 * Description: 浙商银行对账文件通知处理Service<br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/26
 * @title cxdai_interface
 * @package com.cxdai.console.cgnotify.service
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CZBankReconNotifyService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EReconFileMapper eReconFileMapper;
    @Autowired
    private EReconHeaderMapper eReconHeaderMapper;
    @Autowired
    private EReconDetailMapper eReconDetailMapper;
    @Autowired
    private EReconHeaderResultMapper eReconHeaderResultMapper;
    @Autowired
    private EReconDetailResultMapper eReconDetailResultMapper;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private AccountLogMapper accountLogMapper;
    @Autowired
    private CashRecordMapper cashRecordMapper;

    /**
     * 定义 加密算法,可用 DES,DESede,Blowfish
     * keybyte为加密密钥，长度为24字节
     * src为被加密的数据缓冲区（源）
     */
    private static final String ALGORITHM = "DESede";
    /**
     * 3DES的KEY，用于解密
     */
    private static final String FILE_3DES_KEY = "192837464637281964738291";

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 从FTP服务器下载对账文件
     *
     * @param type     对账文件类型
     * @param filename 对账文件名称
     * @throws Exception
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String downloadFileFromFtp(int type, String filename) throws Exception {
        String tempFilePath = null;
        try {
            SFTPUtil util = new SFTPUtil();
            // 连接FTP服务器
            util.connect();
            File tempDir = Files.createTempDir();
            // 下载对账文件
            util.download(filename, tempDir.getAbsolutePath());
            // 获取已下载文件的路径
            tempFilePath = tempDir.getAbsolutePath() + File.separator + filename;
            // 关闭FTP文件
            util.close();
            filename = getFilename(filename);
            // 添加对账记录
            this.addReconFileRecord(type, "FILE_DOWNLOAD", 1, "下载成功", filename);
        } catch (Exception e) {
            logger.error("下载文件失败", e);
            // 添加对账记录
            this.addReconFileRecord(type, "FILE_DOWNLOAD", 0, "下载失败", filename);
        }

        return tempFilePath;
    }

    /**
     * 验证摘要文件
     *
     * @param filename 文件名称
     * @param digest   摘要sha1
     * @return 验证结果
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean checkDigest(int type, String filename, String digest)
            throws IOException, NoSuchAlgorithmException {
        boolean isValid = digest.toLowerCase().equals(sha1(new File(filename)).toLowerCase());
        if (!isValid) {
            logger.error("摘要验证失败", filename);
            // 添加对账记录
            this.addReconFileRecord(type, "CHECK_DIGEST", 0, "摘要验证失败", filename);
        }
        return isValid;
    }

    public static void main(String[] args) throws IOException {
        CZBankReconNotifyService service = new CZBankReconNotifyService();
        service.unzipFile(1, "C:\\Users\\Administrator\\Desktop\\MER_20160623_001.zip");
    }

    /**
     * 解密并解压文件
     *
     * @param type     类型
     * @param filename 文件名称
     * @return 解压后的文件名称
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String unzipFile(int type, String filename) throws IOException {
        File targetFile = null;
        InputStream inputStream = null;
        ZipInputStream zis = null;
        BufferedWriter output = null;
        StringBuilder fileContent = new StringBuilder();
        try {
            inputStream = new FileInputStream(filename);
            inputStream = decryptMode(FILE_3DES_KEY, inputStream);
            //解密
            //文件解压
            zis = new ZipInputStream(inputStream);
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    zis.closeEntry();
                    continue;
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        zis));
                String s;
                while ((s = in.readLine()) != null)
                    fileContent.append(s).append("\n");
                zis.closeEntry();
            }
            String parentPath = getParentDirOfFile(filename);
            targetFile = new File(parentPath, getFilename(filename) + ".csv");
            output = new BufferedWriter(new FileWriter(
                    targetFile));
            output.write(fileContent.toString());
            // 考虑到解压一般不会失败，成功不进行记录
            // this.addReconFileRecord(type, "FILE_UNZIP", 1, "解压文件成功", filename);
        } catch (IOException e) {
            logger.error("解压文件失败", e);
            this.addReconFileRecord(type, "FILE_UNZIP", 0, "解压文件失败", filename);
            throw e;
        } finally {
            closeResources(output);
            closeResources(inputStream);
            closeResources(zis);
        }
        return targetFile.getAbsolutePath();
    }

    /**
     * 保存CSV数据
     *
     * @param csvFile   csv文件
     * @param projectId 项目ID
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public EReconHeader saveCSVData(int type, String csvFile, String projectId)
            throws IOException, ParseException {
        String remark = getRemark(type);
        String filename = getFilename(csvFile);
        EReconHeader eReconHeader = null;
        try {
            // 删除旧的汇总数据
            eReconHeaderMapper.deleteByFilename(filename);
            // 删除旧的明细数据
            eReconDetailMapper.deleteByFilename(filename);
            int rowIndex = 0;
            // 读取并解析CSV文件
            Scanner scanner = new Scanner(new FileReader(csvFile));
            while (scanner.hasNext()) {
                String dataInRow = scanner.nextLine();
                String[] rowData = dataInRow.split(",");
                if (rowIndex == 0) {
                    // 处理汇总数据
                    eReconHeader = this.saveHeaderData(type, rowData, filename, remark);
                } else {
                    // 处理明细数据
                    this.saveDetailData(type, rowData, projectId, eReconHeader.getId(),
                            filename, remark);
                }
                rowIndex++;
            }
            this.addReconFileRecord(type, "SAVE_DATA", 1, "保存" + remark + "数据成功", filename);
        } catch (Exception e) {
            logger.error("保存" + remark + "数据失败", e);
            this.addReconFileRecord(type, "SAVE_DATA", 0, "保存" + remark + "数据失败", filename);
            throw e;
        }
        return eReconHeader;
    }

    public Page queryListForPage(ReconCnd reconCnd, Integer curPage, Integer pageSize) {
        Page page = new Page(curPage, pageSize);
        Integer totalCount = eReconFileMapper.queryListCountByCnd(reconCnd);
        page.setTotalCount(totalCount);
        List<EReconFile> list = eReconFileMapper.queryListByCnd(reconCnd, page);
        page.setResult(list);
        return page;
    }

    private String getRemark(int type) {
        String remark = "";
        if (type == 1) {
            remark = "批量扣款";
        } else if (type == 2) {
            remark = "批量还款";
        } else if (type == 3) {
            remark = "开销户";
        } else if (type == 4) {
            remark = "出入账";
        } else {
            throw new RuntimeException("不支持的异常类型");
        }
        return remark;
    }

    /**
     * 对账、保存对账结果
     *
     * @param projectId 项目ID
     * @param filename  文件名称
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean saveReconResult(int type, String projectId, Integer projectStatus,
                                   Integer headerId, String filename) {
        String remark = getRemark(type);
        // 是否流标
        boolean isFlowBorrow = type == 1 && projectStatus != null && projectStatus == 0;
        try {
            filename = getFilename(filename);
            // 删除汇总对账结果
            eReconHeaderResultMapper.deleteByFilename(filename);
            // 删除明细对账结果
            eReconDetailResultMapper.deleteByFilename(filename);

            // 汇总数据对比
            // 查询系统汇总数据
            EReconHeaderVo sysHeader = sumHeader(isFlowBorrow ? -1 : type, projectId);
            // 查询银行汇总数据
            EReconHeaderVo bankHeader = eReconHeaderResultMapper.queryHeader(filename);
            // 流标对账结果和实时响应结果是相反的
            if (isFlowBorrow) {
                Integer failCount = bankHeader.getFailCount();
                Integer successCount = bankHeader.getSuccessCount();
                BigDecimal failTotal = bankHeader.getFailTotal();
                BigDecimal successTotal = bankHeader.getSuccessTotal();
                bankHeader.setFailCount(successCount);
                bankHeader.setSuccessCount(failCount);
                bankHeader.setFailTotal(successTotal);
                bankHeader.setSuccessTotal(failTotal);
            }
            // 汇总数据对比
            boolean compareResult = compareHeader(sysHeader, bankHeader, projectId, headerId, filename);
            // 新增汇总数据对比结果
            if (compareResult) {
                this.addReconFileRecord(type, "RECON_HEADER_RESULT", 1, remark + "汇总对账成功", filename);
            } else {
                this.addReconFileRecord(type, "RECON_HEADER_RESULT", 0, remark + "汇总对账失败", filename);
            }

            // 流标不进行明细数据对账
            if (!isFlowBorrow) {
                // 明细数据对比
                // 查询系统明细数据
                Borrow borrow = borrowMapper.queryByProjectId(projectId);
                Assert.notNull(borrow, "borrow cannot null");
                List<EReconResultVo> eReconResults = queryDetail(type, borrow.getId(), filename);
                for (EReconResultVo resultVo : eReconResults) {
                    compareResult = compareDetail(resultVo, projectId, headerId, resultVo.getDetailId(),
                            filename) && compareResult;
                }
                // 新增明细数据对比结果
                if (compareResult) {
                    this.addReconFileRecord(1, "RECON_DETAIL_RESULT", 1, remark + "明细对账成功", filename);
                } else {
                    this.addReconFileRecord(1, "RECON_DETAIL_RESULT", 0, remark + "明细对账失败", filename);
                }
            }

            return compareResult;
        } catch (Exception e) {
            logger.error(remark + "对账异常", e);
            this.addReconFileRecord(1, "SAVE_RESULT", 0, remark + "对账异常", filename);
            throw e;
        }
    }

    private EReconHeaderVo sumHeader(int type, String targetId) {
        if (type == 1) {
            return eReconHeaderResultMapper.sumBTFHeader(targetId);
        } else if (type == 2) {
            return eReconHeaderResultMapper.sumBRFHeader(targetId);
        } else if (type == -1) {
            return eReconHeaderResultMapper.queryBorrowHeader(targetId);
        }
        throw new RuntimeException("不支持的异常类型");
    }

    private List<EReconResultVo> queryDetail(int type, Integer targetId, String targetId2) {
        if (type == 1) {
            return eReconDetailResultMapper.queryBTFDetail(targetId, targetId2);
        } else if (type == 2) {
            return eReconDetailResultMapper.queryBRFDetail(targetId, targetId2);
        }
        throw new RuntimeException("不支持的异常类型");
    }

    /**
     * 对比汇总数据
     *
     * @param sysHeader  系统汇总数据
     * @param bankHeader 银行汇总数据
     */
    private boolean compareHeader(EReconHeaderVo sysHeader, EReconHeaderVo bankHeader,
                                  String projectId, Integer headerId, String filename) {
        int result = 1;

        // 数据缺失对比
        boolean compareResult = this.compareIsNull(sysHeader, bankHeader, 1, projectId,
                headerId, null, filename);

        // 不缺失数据继续对比
        if (compareResult) {
            // 成功笔数不相等
            compareResult = this.compareValue(sysHeader.getSuccessCount(), bankHeader.getSuccessCount(),
                    "successCount", 1, projectId, headerId, null, filename);

            // 成功金额不相等
            compareResult = this.compareValue(sysHeader.getSuccessTotal(), bankHeader.getSuccessTotal(),
                    "successTotal", 1, projectId, headerId, null, filename) && compareResult;

            // 失败笔数不相等
            compareResult = this.compareValue(sysHeader.getFailCount(), bankHeader.getFailCount(),
                    "failCount", 1, projectId, headerId, null, filename) && compareResult;

            // 失败金额不相等
            compareResult = this.compareValue(sysHeader.getFailTotal(), bankHeader.getFailTotal(),
                    "failTotal", 1, projectId, headerId, null, filename) && compareResult;

            Integer sysStatus = sysHeader.getStatus();
            Integer bankStatus = bankHeader.getStatus();
            if (bankStatus == null) {
                return compareResult;
            }
            if (sysStatus != 7 && bankStatus == 0) {
                // 流标 借款标状态不等于7（流标，银行处理中）
                // 数值错误
                result = -1;
                // 插入对账结果
                insertHeaderResult(sysStatus, bankStatus, result, 1, "status",
                        projectId, headerId, filename);
                compareResult = false;
            } else if (sysStatus != 6 && bankStatus == 1) {
                // 满标 借款标状态不等于6（满标，银行处理中）
                // 数值错误
                result = -1;
                // 插入对账结果
                insertHeaderResult(sysStatus, bankStatus, result, 1, "status",
                        projectId, headerId, filename);
                compareResult = false;
            }
        }
        return compareResult;
    }

    private boolean compareDetail(EReconResultVo eReconResult, String projectId,
                                  Integer headerId, Integer detailId, String filename) {
        boolean compareResult = false;

        // 系缺失对比
        compareResult = this.compareIsNull(eReconResult.getSysNo(), eReconResult.getBankNo(),
                1, projectId, headerId, detailId, filename);

        // 状态对比
        compareResult = this.compareValue(eReconResult.getSysStatus(), eReconResult.getBankStatus(),
                "status", 1, projectId, headerId, detailId, filename);

        // 金额对比
        compareResult = this.compareValue(eReconResult.getSysAccount(), eReconResult.getBankAccount(),
                "money", 1, projectId, headerId, detailId, filename);

        // 手续费对比
        compareResult = this.compareValue(eReconResult.getSysFee(), eReconResult.getBankFee(),
                "fee", 1, projectId, headerId, detailId, filename);
        return compareResult;
    }

    private boolean compareIsNull(Object sysVal, Object bankVal,
                                  Integer targetType, String targetId,
                                  Integer headerId, Integer detailId, String filename) {
        int result = 1;
        boolean compareResult = true;
        if (sysVal == null) {
            // 本系统缺失
            compareResult = false;
            insertResult(null, null, -3, targetType, null,
                    targetId, headerId, detailId, filename);
        }
        if (bankVal == null) {
            // 银行缺失
            compareResult = false;
            // 插入对比结果
            insertResult(null, null, -3, targetType, null,
                    targetId, headerId, detailId, filename);
        }
        return compareResult;
    }

    private boolean compareValue(Object sysVal, Object bankVal, String targetName,
                                 Integer targetType, String targetId,
                                 Integer headerId, Integer detailId, String filename) {
        // 如果都为空，默认比不进行对比
        if (sysVal == null && bankVal == null) {
            return true;
        }
        int result = 1;
        if (sysVal instanceof BigDecimal &&
                bankVal instanceof BigDecimal) {
            BigDecimal sys = (BigDecimal) sysVal;
            BigDecimal bank = (BigDecimal) bankVal;
            // 对账结果
            result = sys.compareTo(bank) == 0 ? 1 : -1;
        } else if (sysVal instanceof Integer &&
                bankVal instanceof Integer) {
            Integer sys = (Integer) sysVal;
            Integer bank = (Integer) bankVal;
            // 对账结果
            result = sys.compareTo(bank) == 0 ? 1 : -1;
        }
        // 插入对比结果
        insertResult(sysVal, bankVal, result, targetType, targetName,
                targetId, headerId, detailId, filename);
        return result == 1;
    }

    private void insertResult(Object sysVal, Object bankVal, int result,
                              Integer targetType, String targetName, String targetId,
                              Integer headerId, Integer detailId, String filename) {
        // 插入对账结果
        if (detailId == null) {
            insertHeaderResult(sysVal, bankVal, result, targetType, targetName,
                    targetId, headerId, filename);
        } else {
            insertDetailResult(sysVal, bankVal, result, targetType, targetName,
                    targetId, headerId, detailId, filename);
        }
    }

    private void insertHeaderResult(Object sysVal, Object bankVal, int result,
                                    Integer targetType, String targetName, String targetId,
                                    Integer headerId, String filename) {
        EReconHeaderResult record = new EReconHeaderResult();
        record.setFileName(filename);
        record.setAdduser(-2);
        record.setSysValue(obj2Str(sysVal));
        record.setBankValue(obj2Str(bankVal));
        record.setResult(result);
        record.setHeaderId(headerId);
        record.setTargetId(targetId);
        record.setTargetType(targetType);
        record.setTargetName(targetName);
        record.setRemark(getRemark(sysVal, bankVal, targetName));
        eReconHeaderResultMapper.insertSelective(record);
    }

    private void insertDetailResult(Object sysVal, Object bankVal, int result,
                                    Integer targetType, String targetName, String targetId,
                                    Integer headerId, Integer detailId, String filename) {
        EReconDetailResult record = new EReconDetailResult();
        record.setFileName(filename);
        record.setAdduser(-2);
        record.setSysValue(obj2Str(sysVal));
        record.setBankValue(obj2Str(bankVal));
        record.setResult(result);
        record.setHeaderId(headerId);
        record.setDetailId(detailId);
        record.setTargetId(targetId);
        record.setTargetType(targetType);
        record.setTargetName(targetName);
        record.setRemark(getRemark(sysVal, bankVal, targetName));
        eReconDetailResultMapper.insertSelective(record);
    }

    private String getRemark(Object sysVal, Object bankVal, String targetName) {
        String remark = "【本系统】%s：%s，【银行】%s：%s";

        if (sysVal == null) {
            remark = "本系统缺失";
        } else if (bankVal == null) {
            remark = "银行缺失";
        } else {
            remark = String.format(remark, targetName, sysVal, targetName, bankVal);
        }
        return remark;
    }

    private String obj2Str(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    /**
     * 保存汇总数据
     *
     * @param row      行数据
     * @param filename 文件名称
     */
    private EReconHeader saveHeaderData(int type, String[] row,
                                        String filename, String remark) {
        String targetId = "", status = "",
                yesNum = "", yesMoney = "",
                noNum = "", noMoney = "";

        // 根据类型处理数据
        if (type == 1) {
            // 批量扣款
            targetId = row[0];
            status = row[1];
            yesNum = row[2];
            yesMoney = row[3];
            noNum = row[4];
            noMoney = row[5];
        } else if (type == 2) {
            // 批量还款
            targetId = row[0];
            yesNum = row[1];
            yesMoney = row[2];
            noNum = row[3];
            noMoney = row[4];
        } else if (type == 4) {
            // 出入账
            yesNum = row[0];
            yesMoney = row[1];
            noNum = row[2];
            noMoney = row[3];
        }

        EReconHeader record = new EReconHeader();
        record.setTargetId(targetId);
        record.setTargetType(type);
        record.setStatus(toInteger(status));
        record.setYesNum(toInteger(yesNum));
        record.setYesMoney(toBigDecimal(yesMoney));
        record.setNoNum(toInteger(noNum));
        record.setNoMoney(toBigDecimal(noMoney));
        record.setAdduser(-2);
        record.setFileName(filename);
        record.setRemark(remark + "汇总数据");
        eReconHeaderMapper.insertSelective(record);
        return record;
    }

    /**
     * 保存明细数据
     *
     * @param row       行数据
     * @param projectId 项目ID
     * @param filename  文件名称
     */
    private void saveDetailData(int type, String[] row, String projectId, Integer headerId,
                                String filename, String remark)
            throws ParseException {
        String p2pNo = "", bankNo = "",
                status = "", account = "",
                fee = "", tradeDate = "",
                money2 = "", status2 = "",
                status3 = "", expType = "";

        // 根据类型处理数据
        if (type == 1 || type == 2) {
            // 批量扣款和批量还款
            p2pNo = row[0];
            bankNo = row[1];
            status = row[2];
            account = row[3];
            fee = row[4];
            tradeDate = row[5];
        } else if (type == 4) {
            // 出入账
            p2pNo = row[0];
            bankNo = row[1];
            account = row[2];
            money2 = row[3];
            fee = row[4];
            status = row[5];
            status2 = row[6];
            status3 = row[7];
            expType = row[8];
            tradeDate = row[9];
        }

        EReconDetail record = new EReconDetail();
        record.setHeaderId(headerId);
        record.setP2pNo(p2pNo);
        record.setBankNo(bankNo);
        record.setStatus(toInteger(status));
        record.setStatus2(toInteger(status2));
        record.setStatus3(toInteger(status3));
        record.setMoney(toBigDecimal(account));
        record.setMoney2(toBigDecimal(money2));
        record.setFee(toBigDecimal(fee));
        record.setTradetime(toDate(tradeDate));
        record.setTargetId(projectId);
        record.setTargetType(type);
        record.setExpType(toInteger(expType));
        record.setFileName(filename);
        record.setAdduser(-2);
        record.setRemark(remark + "明细数据");
        eReconDetailMapper.insertSelective(record);
    }

    private Integer toInteger(String intVal) {
        if (StringUtils.hasLength(intVal)) {
            return Integer.parseInt(intVal);
        }
        return null;
    }

    private BigDecimal toBigDecimal(String decVal) {
        if (StringUtils.hasLength(decVal)) {
            return new BigDecimal(decVal).divide(BigDecimal.valueOf(100));
        }
        return null;
    }

    private Date toDate(String date) throws ParseException {
        if (StringUtils.hasLength(date)) {
            return dateFormatter.parse(date);
        }
        return null;
    }

    /**
     * 关闭资源
     *
     * @param closeable 可自动关闭的对象
     */
    private void closeResources(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            logger.error("close resources error", e);
        }
    }

    /**
     * 获取文件名不包含扩展名
     *
     * @param filePath 文件路径
     * @return 文件名称
     */
    private String getFilename(String filePath) {
        return Files.getNameWithoutExtension(filePath);
    }

    /**
     * 获取父目录
     *
     * @param filePath 文件路径
     * @return 父目录
     */
    private String getParentDirOfFile(String filePath) {
        return FilenameUtils.getFullPathNoEndSeparator(filePath);
    }

    /**
     * 通过sha1算法进行文件摘要
     *
     * @param file 文件
     * @return 摘要结果
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private String sha1(File file) throws NoSuchAlgorithmException, IOException {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA1");

        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            final byte[] buffer = new byte[1024];
            for (int read = 0; (read = is.read(buffer)) != -1; ) {
                messageDigest.update(buffer, 0, read);
            }
        }
        // Convert the byte to hex format
        try (Formatter formatter = new Formatter()) {
            for (final byte b : messageDigest.digest()) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }
    }

    /**
     * 添加对账记录
     *
     * @param type   对账类型
     * @param status 状态：0、失败；1、成功
     * @param remark 备注
     */
    private void addReconFileRecord(int type, String targetName, int status, String remark,
                                    String filename) {
        EReconFile eReconFile = new EReconFile();
        eReconFile.setTargetName(targetName);
        eReconFile.setTargetType(type);
        eReconFile.setStatus(status);
        eReconFile.setAdduser(-2);
        eReconFile.setRemark(remark);
        eReconFile.setFileName(getFilename(filename));
        eReconFileMapper.insertSelective(eReconFile);
    }

    /**
     * 设置数据流为解密模式
     *
     * @param des3Key     解密key
     * @param inputStream 输入流
     * @return CipherInputStream
     */
    private CipherInputStream decryptMode(String des3Key, InputStream inputStream) {
        //生成密钥
        SecretKey deskey = new SecretKeySpec(des3Key.getBytes(), ALGORITHM); //解密
        return decryptMode(deskey, inputStream);
    }

    /**
     * 设置数据流为解密模式
     *
     * @param secretKey   秘钥
     * @param inputStream 输入流
     * @return CipherInputStream
     */
    private CipherInputStream decryptMode(SecretKey secretKey, InputStream inputStream) {
        try {
            Cipher c1 = Cipher.getInstance(ALGORITHM);
            c1.init(Cipher.DECRYPT_MODE, secretKey);
            return new CipherInputStream(inputStream, c1);
        } catch (Exception e) {
            logger.error("decryptMode error", e);
        }
        return null;
    }

    public List<EReconFile> selectByFilename(String filename) {
        return eReconFileMapper.selectByFilename(filename);
    }

    public Map<String, Object> queryBiotfReconByCnd(ReconResultCnd reconResultCnd) throws Exception {
        AccountLogCnd accountLogCnd = new AccountLogCnd();
        accountLogCnd.setBeginTime(reconResultCnd.getBeginTime());
        accountLogCnd.setEndTime(reconResultCnd.getEndTime());
        CashRecordCnd cashRecordCnd = new CashRecordCnd();
        cashRecordCnd.setIsCustody(1);

        if (null != accountLogCnd.getBeginTime()) {
            accountLogCnd.setBeginTime(DateUtils.convert2StartDate(accountLogCnd.getBeginTime()));
            accountLogCnd.setBeginTimeStr(accountLogCnd.getBeginTime().getTime() / 1000 + "");
            reconResultCnd.setBeginTimeStr(accountLogCnd.getBeginTimeStr());
            cashRecordCnd.setBeginTimeStr(reconResultCnd.getBeginTimeStr());
        }
        if (null != accountLogCnd.getEndTime()) {
            accountLogCnd.setEndTime(DateUtils.convert2EndDate(accountLogCnd.getEndTime()));
            accountLogCnd.setEndTimeStr(accountLogCnd.getEndTime().getTime() / 1000 + "");
            reconResultCnd.setEndTimeStr(accountLogCnd.getEndTimeStr());
            cashRecordCnd.setEndTimeStr(reconResultCnd.getEndTimeStr());
        }

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> map = accountLogMapper.queryAccoutLogByRecharge(accountLogCnd);

        if (map != null && map.get("czbankRechargeTotal") != null) {
            //国诚存管充值总额
            resultMap.put("gcjrRechargeTotal", new BigDecimal(map.get("czbankRechargeTotal").toString()));
        } else {
            resultMap.put("gcjrRechargeTotal", new BigDecimal(0));
        }

        // 浙商存管对账文件统计总额
        Map<String, Object> reconObjMap = eReconDetailMapper.sumReconDetailByCnd(reconResultCnd);
        if (reconObjMap != null) {
            resultMap.putAll(reconObjMap);
        }

        this.compareAndSaveResult(reconObjMap, resultMap,
                "gcjrRechargeTotal", "czbankRechargeMoney", "rechargeCompareResult");

        map = cashRecordMapper.queryCashRecorData(cashRecordCnd);
        if (map != null && map.get("sum_total") != null) {
            //国诚存管提现总额
            resultMap.put("gcjrTakeCashTotal", new BigDecimal(map.get("sum_total").toString()));
        } else {
            resultMap.put("gcjrTakeCashTotal", new BigDecimal(0));
        }

        this.compareAndSaveResult(reconObjMap, resultMap,
                "gcjrTakeCashTotal", "czbankTakeCashMoney", "takeCashCompareResult");

        Map<String, Object> tenderObjMap = eReconDetailMapper.sumTenderRecordByCnd(reconResultCnd);
        if (tenderObjMap != null) {
            resultMap.putAll(tenderObjMap);
        }

        this.compareAndSaveResult(reconObjMap, resultMap,
                "sysInvestMoney", "czbankInvestMoney", "investMoneyCompareResult");

        this.compareAndSaveResult(reconObjMap, resultMap,
                "sysInvestSuccessMoney", "czbankInvestSuccessMoney", "investSuccessMoneyCompareResult");

        return resultMap;
    }

    private void compareAndSaveResult(Map<String, Object> reconObjMap, Map<String, Object> resultMap,
                                      String compareKey, String compareKey2, String resultKey) {
        Object obj = null;
        BigDecimal gcjrTotal = (BigDecimal) resultMap.get(compareKey);
        if (reconObjMap != null) {
            obj = reconObjMap.get(compareKey2);
        }
        if (obj != null) {
            BigDecimal czbankMoney = new BigDecimal(obj.toString());
            if (gcjrTotal.compareTo(czbankMoney) == 0) {
                resultMap.put(resultKey, true);
            } else {
                resultMap.put(resultKey, false);
            }
        } else {
            resultMap.put(resultKey, true);
        }

    }
}
