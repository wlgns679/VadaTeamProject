<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/jsp/top.jsp" />

<script src="${webapproot}/js/common.js"></script>
 
<main>
	<div class="container">
		<div class="row">
			
			<!-- 리뷰작성을 위한 데이터를 담은 폼/ 게시글에 해당하는 게시판테이블에 리뷰작성 (리뷰테이블 따로 존재X) -->
			<form method="post" action="${webapproot}/reviewproc.do?productnum=${param.productnum}">

				<table class="table table-stripped"
					style="text-align: center; boarder: 1px solid #dddddd">

					<thead>
						<tr>
							<th colspan="2"
								style="background-color: #eeeeee; text-align: center;">후기글 작성</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td><textarea class="form-control" placeholder="후기글 내용"
									name="review" maxlength="2048" style="height: 350px"></textarea></td>
						</tr>
						<tr>
							<td>
								<p style="float: left; font-size: 20px">별점 :</p> 
								
								<!-- 별점에 해당하는 점수 -->
								<select name="reviewscore" style="float: right">
									<option value="1">★☆☆☆☆</option>
									<option value="2">★★☆☆☆</option>
									<option value="3">★★★☆☆</option>
									<option value="4">★★★★☆</option>
									<option value="5">★★★★★</option>
							</select>
							
							</td>
						</tr>
					</tbody>
				</table>
			
            <input type="submit" class="btn btn-primary pull-right" value="후기작성" onclick="javascript:confirmCommand('${webapproot}/reviewproc.do?productnum=${param.productnum}','후기작성');">
             <button type="button" class="btn btn-primary pull-right" onclick="history.back();">구매목록</button>
			</form>
		</div>
	</div>

</main>

<jsp:include page="/jsp/bottom.jsp" />