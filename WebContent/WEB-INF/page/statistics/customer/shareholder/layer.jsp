<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center" border="1" bordercolor="black" style="width: 95%">
			<tr>
				<td align="center" width="15%" bgcolor="#ededed">加权待收</td>
				<td align="center" width="15%">
					<c:if test="${shareholderRankVo.dayInterstStr==null}">无</c:if>
					<c:if test="${shareholderRankVo.dayInterstStr!=null}">${shareholderRankVo.dayInterstStr}</c:if>
				</td>
				<td align="center" width="20%" bgcolor="#ededed">加权待收排名</td>
				<td align="center" width="13.3%" >
					<c:if test="${shareholderRankVo.dayInterstRank==null}">无</c:if>
					<c:if test="${shareholderRankVo.dayInterstRank!=null}">${shareholderRankVo.dayInterstRank}</c:if>
				</td>
				<td align="center" width="20%" bgcolor="#ededed">加权待收排名得分</td>
				<td align="center" width="13.3%" >
					<c:if test="${shareholderRankVo.dayInterstScore==null}">无</c:if>
					<c:if test="${shareholderRankVo.dayInterstScore!=null}">${shareholderRankVo.dayInterstScore}</c:if>
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#ededed">累计总积分</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.accumulatepoints==null}">无</c:if>
					<c:if test="${shareholderRankVo.accumulatepoints!=null}">${shareholderRankVo.accumulatepoints}</c:if>
				</td>
				<td align="center" bgcolor="#ededed">累计总积分排名</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.accumulatepointsRank==null}">无</c:if>
					<c:if test="${shareholderRankVo.accumulatepointsRank!=null}">${shareholderRankVo.accumulatepointsRank}</c:if>
				</td>
				<td align="center" bgcolor="#ededed">累计总积分排名得分</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.accumulatepointsScore==null}">无</c:if>
					<c:if test="${shareholderRankVo.accumulatepointsScore!=null}">${shareholderRankVo.accumulatepointsScore}</c:if>
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#ededed">投标直通车总额</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.firstTenderTotal==null}">无</c:if>
					<c:if test="${shareholderRankVo.firstTenderTotal!=null}">${shareholderRankVo.firstTenderTotal}</c:if>
				</td>
				<td align="center" bgcolor="#ededed">投标直通车总额排名</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.firstTenderTotalRank==null}">无</c:if>
					<c:if test="${shareholderRankVo.firstTenderTotalRank!=null}">${shareholderRankVo.firstTenderTotalRank}</c:if>
				</td>
				<td align="center" bgcolor="#ededed">投标直通车总额排名得分</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.firstTenderTotalScore==null}">无</c:if>
					<c:if test="${shareholderRankVo.firstTenderTotalScore!=null}">${shareholderRankVo.firstTenderTotalScore}</c:if>
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#ededed">推广人数</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.extensionNumber==null}">无</c:if>
					<c:if test="${shareholderRankVo.extensionNumber!=null}">${shareholderRankVo.extensionNumber}</c:if>
				</td>
				<td align="center" bgcolor="#ededed">推广人数排名</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.extensionNumberRank==null}">无</c:if>
					<c:if test="${shareholderRankVo.extensionNumberRank!=null}">${shareholderRankVo.extensionNumberRank}</c:if>
				</td>
				<td align="center" bgcolor="#ededed">推广人数排名得分</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.extensionNumberScore==null}">无</c:if>
					<c:if test="${shareholderRankVo.extensionNumberScore!=null}">${shareholderRankVo.extensionNumberScore}</c:if>
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#ededed">综合得分</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.totalScore==null}">无</c:if>
					<c:if test="${shareholderRankVo.totalScore!=null}">${shareholderRankVo.totalScore}</c:if>
				</td>
				<td align="center" bgcolor="#ededed">综合排名</td>
				<td align="center" >
					<c:if test="${shareholderRankVo.totalRank==null}">无</c:if>
					<c:if test="${shareholderRankVo.totalRank!=null}">${shareholderRankVo.totalRank}</c:if>
				</td>
				<td align="center" bgcolor="#ededed"></td>
				<td align="center" ></td>
			</tr>
			  	
		</table>
	</form>
</body>
</html>
