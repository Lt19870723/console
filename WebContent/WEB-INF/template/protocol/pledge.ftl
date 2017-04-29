<#setting number_format="#,##0.00">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body style="margin:0;padding:0;height:100%;width:100%;font-family:Microsoft YaHei,'微软雅黑',SimSun, Arial,Helvetica,Tahoma;color:#555; background:#fff;_background-attachment:fixed; font-size:13px;">
<!--内容开始-->
<div style="padding:10px 10px; margin:0 auto;">
<h5 style="text-align:center; padding:20px 0 20px 0; font-size:18px;">借款咨询与管理服务合同</h5>
    <div style="width:98%; text-align:left; margin:0 auto;padding-bottom:20px; ">
	    <table width="100%">
	    	<tr>
	    		<td>借款标编号：<label style="color: red">${borrowVo.contractNo}</label></td>
	    		<td>
	    			<#if borrowVo.productType?? && (borrowVo.productType==1 || borrowVo.productType==2)>    	
				    	借款类型：<label style="color: red">${(borrowVo.productType==1)?string('诚薪贷','')} ${(borrowVo.productType==2)?string('诚商贷','')}</label>
				    </#if>
	    		</td>
	    		<td>签订日期：<label style="color: red">${borrowVo.successTimeDate?string('yyyy年MM月dd日')}</label></td>
	    	</tr>
	    </table>     
	</div>
<div style="margin:0;padding:0;">
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden;"><strong>甲方（出借人）:</strong>（&lt;出借人名单&gt;详见附件一）</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden;"><strong>乙方（借款人）:</strong>（国诚金融注册用户名：<label style="color: red;">${username}</label>）</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>丙方（咨询服务方）：上海国诚金融信息服务有限公司</strong></p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">通迅地址：上海市虹口区西江湾路388号龙之梦A座32楼</p>
     
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>鉴于：</strong></p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">1.甲、乙双方均已在国诚金融网站注册，并承诺：在签订本合同前，甲、乙双方均已仔细阅读、充分理解并愿意遵守国诚金融网站上与借贷有关的各项规则及说明（包括但不限于注册协议、收费规则、隐私规则及国诚金融网站不时修订并补充的规则等），并将据此承担相应的法律责任。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">2.甲方所有出借人现不可撤销地委托（国诚金融注册用户名：<label style="color: red;">${businessName}</label>）作为债权人代表。该债权人代表有权代其签订与之相关的借款合同；办理、注销抵押权登记；办理公证；划拨出借款项等一系列借款相关事宜。该债权人代表应严格按照甲方的利益从事代理行为，如出现不当行为，甲方可随时更换债权人代表。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">3.各方关系：丙方为一家合法成立并有效存续的有限责任公司，拥有 www.gcjr.com网站（以下简称“国诚金融网站”）的经营管理权，提供借款信息咨询及管理等服务；甲方指将自有合法资金通过丙方出借给资金需求方的人，必须为丙方的有效注册用户，不限于单一主体，但须为自然人；乙方是指从甲方获得资金的资金需求方，必须为丙方的有效注册用户，不限于单一主体，但须为自然人、法人或其他组织。乙方有资金需求，甲方亦同意出借，双方均有意通过丙方建立借贷关系。据此，各方经充分沟通，现协商一致达成如下借款咨询与管理服务合同，以兹共同遵守。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第一条：借款基本信息</strong></p>
    <table style="margin: 0px auto 20px;border-collapse: collapse;border: 1px solid #838383;width:100%;font-size: 12px;">
     <tr>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;" width="30%">借款金额（本金）</td>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;" width="70%">人民币（大写）<label style="color: red">${borrowVo.accountToChinese}</label></td>
     </tr>
     <tr>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;">借款期限</td>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;"><label style="color: red">${borrowVo.timelimitformat}</label>&nbsp;，自本合同生效之日起计算</td>
     </tr>
     <tr>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;">借款利率</td>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;">年利率<label style="color: red">${borrowVo.apr}%</label></td>
     </tr>
     <tr>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;">还款方式</td>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;"><label style="color: red">${borrowVo.styleStr}</label></td>
     </tr>
     <tr>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;">借款用途</td>
     <td style="border: 1px solid #838383; text-align:left; padding:10px 4px;"><label style="color: red">${borrowVo.name}</label></td>
     </tr>
    </table>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第二条：还款计划</strong></p>
     
     <table style="margin: 0px auto 20px;border-collapse: collapse;border: 1px solid #838383;width:100%;font-size: 12px;">
      <tr>
      <td style="border: 1px solid #838383; padding:10px 4px;" align="center" width="20%">期数</td>
      <td style="border: 1px solid #838383; padding:10px 4px;" align="center" width="20%">还款日期</td>
      <td style="border: 1px solid #838383; padding:10px 4px;" align="center" width="20%">当月应还本金</td>
      <td style="border: 1px solid #838383; padding:10px 4px;" align="center" width="20%">当月应还利息</td>	
      <td style="border: 1px solid #838383; padding:10px 4px;" align="center" width="20%">当月应还总额</td>      
      </tr>
      <#if repayList?exists>
	      <#list repayList as repay>
	      <tr>
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">${repay.orderString}</label></td>
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">${repay.repaymentTimeDate?string('yyyy年MM月dd日')}</label></td>
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">￥${repay.capital?string}</label></td>
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">￥${repay.interest?string}</label></td>	        
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">￥${repay.repaymentAccount?string}</label></td>	        
	      </tr>
	      </#list>
      </#if>      
      </table>     
     
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第三条：各方权利与义务</strong></p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">1. 【甲方权利与义务】</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（1）甲方有权根据国诚金融网站上展示的借款人的借款信息决定是否将资金出借给乙方；若甲方决定出借资金，则甲方有权按照本合同约定获得一定的利息收益，同时应承担因乙方未归还借款而导致的利息收益及本金的损失风险；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（2）当甲方和乙方在建立债权债务关系后，甲方有权根据本合同第五条的约定将其债权转让给其他出借人（其他出借人必须是在国诚金融网站上的注册用户），并同时转让与该债权相关的各项权利与义务；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（3）若甲方为多个出借人，且乙方所还款项或风险备用金不足以偿还全部出借人的本金、利息收益时，甲方各出借人之间应按照其借出款项的比例受偿还款；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（4）甲方对乙方的个人信息负有保密义务，承诺仅作为决定是否对其进行出借的参考，不得向任何第三方透露，否则承担由此造成的一切后果；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（5）甲方在交易过程中了解到的丙方的商业秘密、业务模式等，承诺不向任何第三方透露，且该保密义务不随本合同的终止而终止；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（6）甲方保证其用于出借的资金来源合法，并具备合法的所有权。因出借资金归属、合法性问题而引起的纠纷，甲方应自行负责解决，如甲方未能解决的，则甲方应放弃出借资金带来的收益所得。甲方保证其具有出借人的法律资格，不存在违反相关法律法规出借资金的行为；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（7）甲方在资金出借、债权转让过程中所得收益产生的相关税费，由甲方自行向主管税务机关申报、缴纳；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（8）若甲方、丙方发现有影响乙方履约能力的不利变化时（包括但不限于乙方违反本合同相关义务与承诺、乙方财产遭受没收、征用、查封、扣押、冻结等），甲方应允许丙方为保护其利益宣告本合同即刻到期并通知乙方及时还款；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（9）若乙方申请提前还款的，甲方应允许丙方代其核实、同意该申请，同时丙方可确定相应的提前还款日。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">2. 【乙方的权利与义务】</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（1）乙方有权按照本合同的约定使用借款；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（2）乙方应按照本合同的约定按时、足额向甲方偿还本金及利息，向丙方支付借款产生的融资服务费用（融资服务费由乙丙双方另行约定）；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（3）乙方承诺通过本合同项下获得的借款不得用于任何违法用途（包括但不限于赌博、吸毒、贩毒、卖淫嫖娼等）及一切高风险投资（如证券、期货、彩票等）；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（4）乙方应确保其所提供的所有信息及资料文件（包括但不限于借款申请表、授权委托书、担保或抵押手续文件、注册信息等）真实、合法、有效，不得提供虚假信息和隐瞒重要事实；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（5）乙方同意，丙方及其委托机构有权主动收集乙方信息及资料(包括但不限于向征信机构查询乙方的信用信息等),该信息及资料可用于以下用途（包括但不限于）：</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">a. 在国诚金融网站上发布借款需求或对乙方进行信用评估或催收调查；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">b. 可将乙方的个人信息和还款情况提供给征信机构，若乙方逾期还款，丙方可将乙方身份资料及个人详细信息向有关部门或公开渠道（包括乙方所在工作单位、资信管理机构、银行或担保公司等借贷担保机构及平台、新闻媒体、国诚金融网站“黑名单”栏目等）予以通报，实现公告催收；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">c. 用于解决争议、纠纷或法律诉讼等。</p>     
    <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">3.【丙方权利与义务】</p>
    <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（1）丙方有义务核实乙方的各项借款信息及资料，并有权根据乙方提供的各项信息自行决定是否在其国诚金融网站上发布乙方的借款需求；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（2）若丙方审核通过乙方借款申请的，丙方可根据乙方的授权代乙方完成其在国诚金融网站上的用户注册以及借款信息的发布。丙方有权对其提供的信息咨询及管理等服务向乙方收取一定的融资服务费，具体的收费标准由乙丙双方另行约定；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（3）当乙方逾期时，丙方应按照国诚金融网站中的相关规则用其风险备用金对逾期债权进行回购，代乙方先行清偿给甲方。届时丙方即刻取得甲方在本合同项下的全部权利及主张，甲方不得再向乙方主张任何债权，此后乙方应直接向丙方偿还丙方为其先行清偿的全部款项，以及其他费用（如逾期违约金、实现债权的费用等）；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（4）当甲方根据国诚金融网站上的转让规则提出债权转让的，丙方应积极协助甲方进行债权的转让，并根据情况将转让事项以电子邮件或站内信息的形式通知乙方；乙方、丙方对受让人继续承担本合同项下的所有义务；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（5）借款期限内，若乙方欲提前结清借款的，乙方应向丙方提出书面申请，丙方收到乙方申请后有义务按照本合同第四条的约定积极为乙方办理提前结清的相关事宜。</p>
    
    <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第四条：提前结清</strong></p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">1.【提前结清】借款期限内，若发生以下事项时，视为本合同项下借款需提前结清，甲乙双方均委托丙方处理结清相关事宜（包括但不限于在国诚金融网站申请提前还款，结算及划拨本金、利息、费用等）：</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（1）乙方向丙方申请提前还款的，乙方应在提前还款日偿还所有本金、利息。 </p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（2）本合同根据第三条第1款第（8）项、第2款第（3）、（4）项提前到期的，乙方应在本合同到期日之前偿还所有本金、利息。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（3）若发生乙方提前还款的情况，乙方除应偿还剩余本金及利息外，还应按下列标准向甲方支付提前结清补偿金。提前结清补偿金=借款本金*{年利率/365（日）}*2（日）</p>     
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">2.【提前还款金额】提前结清时，利息将结算至提前还款日或提前到期日当天，乙方应一次性偿还所有未还的本金、利息，如不能按约定的日期偿还，还应支付逾期违约金。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">提前还款金额=未还的借款本金余额+实际使用借款期间所产生的利息+提前结清补偿金+逾期违约金（如有）。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第五条：债权债务转让</strong></p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">1.【债权转让】甲方有权将本合同中约定的债权按照国诚金融网站的转让规则转让给第三方（该第三方须为国诚金融网站上的注册用户）。甲方欲转让债权必须书面向丙方提出申请，待丙方审核同意后方可转让。该转让无需征得乙方同意，但丙方可在甲方同意后将转让事项以电子邮件或站内信息的形式通知乙方。债权转让后，本合同项下的其他条款不受影响，乙方仍应按照本合同的约定向债权受让人及丙方履行本合同项下所有义务。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">2.【债务转让】未经甲方、丙方一致同意，乙方不得将本合同中约定的债务转让给任何其他第三方。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">3.【转让后的权利与义务】当发生债权/债务转让时，其他各方均应按照本合同的约定继续向新债权人/债务人履行合同义务并享有相应权利。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第六条：抵押担保</strong></p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">1.【抵押物】乙方为获得甲方出借款项，特提供自己名下财产作为抵押物为该笔借款作担保。担保本合同项下约定的借款本金、利息、逾期违约金、实现债权的费用等。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">2.【抵押登记】乙方有义务与债权人代表前往相关部门办理抵押登记手续，将抵押权登记在债权人代表名下，并将登记证明文件交付给债权人代表保管，若因乙方的原因未及时有效办理抵押登记的，乙方应承担所有责任。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">3.【抵押人承诺】乙方承诺对抵押物拥有完全的、有效的、合法的所有权或处分权，且该抵押物不存在任何争议，未被依法查封、扣押、监管、抵押或者未被采取其他强制性措施。如该抵押物存在其他共有人，则乙方应提供该共有人出具的同意抵押确认书。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">4.【抵押物的使用及处分】乙方未清偿全部债务及费用前，未经债权人同意，乙方不得转让、出租、再行抵押、赠与、托管、重大改装增补或以其他方式处分抵押物的全部或部分；同时，若出现影响抵押物存在或价值的重大事项（如房产拆迁等），乙方须提前书面告知债权人并补充提供或重新提供价值相当的抵押物。抵押物如有价值减少的，乙方应及时恢复抵押物的价值，或者提供债权人认可的、与减少价值相当的担保。否则，债权人代表为保障全体债权人利益有权提前终止本合同行使抵押权，乙方应就抵押物价值减少部分承担赔偿责任。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">5.【抵押权实现情形】出现下列情形之一，抵押权人有权立即实现抵押权：</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（1）乙方未及时、足额的清偿的本金、利息、逾期违约金及其他相关费用的；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（2）乙方及抵押物共有人未如实提供相应资料的；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（3）乙方无力保持抵押物的完整和良好状态、擅自处分抵押物的或出现本合同第六条第4款所述的有损抵押物价值及使用的事由，而乙方拒绝另行提供合格担保的；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（4）乙方发生危及、损害或有可能危及、损害抵押权的其他情形。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">6.【抵押物的处置】若发生本合同第六条第5款所述的情形时，乙方同意抵押权人可选择拍卖、变卖等方式处置该抵押物，所得价款优先偿还其应还的本金、利息、逾期违约金及其他相关费用，否则抵押权人可请求人民法院拍卖抵押物。当乙方逾期时，丙方按照本合同的约定及国诚金融网站中的相关规则先行向甲方履行清偿义务的，届时丙方成为新的债权人，有权就该抵押物拍卖、变卖所得的价款优先受偿。无论何种方式，若处置抵押物所得不足以清偿抵押担保范围内的全部债权的，债权人有权向乙方及其连带保证人追索不足部分；若清偿后有所剩余的，丙方应将剩余部分退还给乙方或其连带保证人。</p>

     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第七条：违约责任</strong></p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">1.合同各方均应严格履行合同义务，非经各方协商一致或依照本合同约定，任何一方不得随意解除本合同。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">2.合同各方均应严格履行合同义务，任何一方违约，违约方应承担因违约致使其他各方产生的费用和损失，包括但不限于调查费、诉讼费、律师费等。如违约方为乙方的，甲方有权立即解除本合同，并要求乙方立即偿还未还的本金、利息、逾期违约金及实现债权的费用等。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">3.乙方应按本合同的约定按时（包括本合同提前到期的约定）、足额偿还所有借款本金、利息及融资服务费；若未能在还款日17:00前足额偿还的，即为逾期。此时乙方需支付逾期违约金，逾期违约金按日计算，每日的逾期违约金为剩余本息的0.1%，直至还清所有本金、利息及借款所产生的相应费用为止。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">4.当乙方发生逾期且乙方账户内的资金不足以偿还所有款项的，应按如下顺序进行清偿：</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（1）实现债权的费用（包括但不限于催收费、诉讼费、处置抵押物的费用等）；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（2）逾期违约金；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（3）逾期的利息；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（4）利息；</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">（5）本金。</p>

     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第八条：法律适用及争议管辖</strong></p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">1.【法律适用】本合同的签订、履行、终止、解释均适用中华人民共和国法律。若本合同中任何条款因现行法律被视为无效的，该条款的无效不影响其他条款的效力。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">2.【争议管辖】本合同在履行过程中，如发生任何争执或纠纷，各方应友好协商解决；若协商不成，任何一方均可向丙方所在地人民法院提起诉讼。诉讼期间，本合同中不涉及争议的条款仍须履行，各方均不得以解决争议为由拒绝履行本合同项下的其他任何义务。</p>
     
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; "><strong>第九条：其他</strong></p>
      
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">1.【合同形式】本合同正文通过国诚金融网站以电子文本形式制成，可有一份或者多份并且每一份均具有同等法律效力。本合同经甲乙双方在丙方的国诚金融网站上以点击确认的方式订立，甲乙双方均委托丙方在其专有服务器上保管本合同，并认可该形式的电子合同的效力。本合同纸质档由甲方（债权人代表）、乙方、丙方三方共同签署后保存在丙方处，丙方应妥善保管该合同文本。三方共同签署同时，国诚金融网站上的相关规则、甲乙双方在国诚金融网站上确认的电子协议（如注册协议等）以及乙方因借款所签署的所有纸质文件（如申请表、授权书等）均为本合同不可分割的组成部分，与本合同具有同等的法律效力。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">2.【合同成立及生效】各方同意并确认，各方通过自行或授权有关方根据国诚金融网站中的有关规则和说明，在国诚金融网站进行借款申请或投标操作等方式确认签署本合同，待丙方审核通过后，本合同立即成立；当乙方在国诚金融网站发布的借款信息满标且其银行账户中收到本合同约定的借款金额之日起，本合同生效，开始计算借款期限、利息及支付融资服务费。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">3.【合同终止】乙方将本合同项下的全部本金、利息、融资服务费、逾期违约金及其他所有相关费用全部偿还完毕后，本合同自动终止。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">4.【信息变更】本合同签订之日起至借款本金、利息、融资服务费、逾期违约金及其他所有相关费用全部清偿完毕之日止，在此期间甲方或乙方的下列信息如发生变更的，其应当在变更后五个工作日内将更新后的信息书面提供给丙方：本人、本人的家庭联系人及紧急联系人、工作单位、居住地址、住所电话、手机号码、电子邮箱、银行账户的变更。若因任何一方不及时提供上述变更信息而带来的损失或额外费用应由该方自行承担。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">5.【信息通知】丙方对甲乙双方的通知将以手机短信、电子邮件、国诚金融网站站内信或国诚金融网站公告等形式进行。</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">6.【合同修订】合同各方同意国诚金融网站根据情况不时对本合同版本进行变更及修订，并同意按照更新版本享有权利及承担义务。本合同的最终解释权归丙方所有。</p>
     
     <p style="page-break-after:always">&nbsp;</p>
     
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden;">甲方：</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden;">日期：</p>
     <p style="height:50px;">&nbsp;</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden;">乙方：</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden;">日期：</p>
     <p style="height:50px;">&nbsp;</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden;">丙方：上海国诚金融信息服务有限公司</p>
     <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden;">日期：</p>
     
     <p style="page-break-after:always">&nbsp;</p>
     
    <p style="padding:0px 10px 10px 10px; line-height:2em;overflow-x:hidden; ">附件一：出借人名单</p>
      <table style="margin: 0px auto 20px;border-collapse: collapse;border: 1px solid #838383;width:100%;font-size: 12px;">
      <tr>
      <td style="border: 1px solid #838383; padding:10px 4px;" width="20%" align="center">出借人网站ID</td>
      <td style="border: 1px solid #838383; padding:10px 4px;" width="20%" align="center">出借金额</td>
      <td style="border: 1px solid #838383; padding:10px 4px;" width="20%" align="center">借款期限</td>
      <td style="border: 1px solid #838383; padding:10px 4px;" width="20%" align="center">年利率</td>      
      <td style="border: 1px solid #838383; padding:10px 4px;" width="20%" align="center">到期应收本息</td>
      </tr>
      <#if tenderRecordVoList?exists>
	      <#list tenderRecordVoList as tenderRecordVo>
	      <tr>
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;">
	        	<#if tenderRecordVo.tenderType==3> 
					<label style="color: blue">（定期宝）</label>
				<#else>
					<label style="color: red">${tenderRecordVo.username?replace('&','&amp;')}</label>
				</#if>
	        </td>
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">￥${tenderRecordVo.account?string}</label></td>
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">${borrowVo.timelimitformat}</label></td>
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">${borrowVo.apr}%</label></td>	        
	        <td align="left" style="border: 1px solid #838383; padding:10px 4px;">
	        	<label style="color: red">
	        		<#if recordType==1 && tenderRecordVo.isVip==0> 
						￥${tenderRecordVo.account?string} 
					<#else>
						￥${tenderRecordVo.repaymentAccount?string} 
					</#if> 
	        	</label>
	        </td>
	      </tr>
	      </#list>
      </#if>
      <tr>
      	<td align="center" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">总计</label></td>
      	<td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">￥${borrowVo.account?string}</label></td>
      	<td align="left" style="border: 1px solid #838383; padding:10px 4px;"></td>
      	<td align="left" style="border: 1px solid #838383; padding:10px 4px;"></td>     
      	<td align="left" style="border: 1px solid #838383; padding:10px 4px;"><label style="color: red">￥${totalRepaymentAccount?string}</label></td>
      </tr>
      </table>
	  <p style=" position:absolute;right:10%;z-index:999;"><div align='right'><img src='${contextPath}/images/tuzhang.png'/></div></p>
</div>
<!--内容结束-->
</div>
</body>
</html>
