<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>感想入力 | 本庭（ほんには）｜まちライブラリー感想カード・メッセージサイト</title>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/start/jquery-ui.css">
	<link rel="stylesheet" href="<c:url value="/css/usercomment.css"/>">
<!--	<link rel="stylesheet" href="../../css/ownercomment.css" /> -->
<!--	<link rel="stylesheet" href="../../css/bibliopost.css" /> -->

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../../js/usercomment.js"></script>

</head>
<body>
<div id="wrapper">

	<!-- ヘッダーここから -->
	<div id="header">
		<h1 id="title">感想入力</h1>
	</div>
	<!-- /header -->

    <!-- 入力 -->
    <div id="edit">
		<form id="frm" action=<c:url value ="/web/admin/usercomment"></c:url> method="post">
			<table>
				<tr>
					<td>書籍番号</td>
					<td>
						<c:out value="${ it.book.id }" />
						<input type="hidden" name="bookId" value="<c:out value="${ it.book.id }" />">
						<input type="hidden" name="commentId" value="<c:out value="${ it.commentId}" />">
						<input type="hidden" name="isbn" value="<c:out value="${ it.book.isbn }" />">
<!-- 						<input type="hidden" name="ownerId" value="<c:out value="${ it.book.ownerComment.ownerId }" />"> -->
					</td>
					<td>書籍名</td>
					<td>
						<c:out value="${ it.book.title }" />
						<input type="hidden" name="title" value="<c:out value="${ it.book.title }" />">
					</td>
				</tr>
				<tr>
					<td>名前</td>
					<td>
					<input type="text" size="15" name="username" id="username" value="<c:out value="${ it.inputName }" />">
					</td>
					<td>感想日付</td>
					<td>
						<input type="text" maxlength="4" size="4" name="year" id="year" value="<c:out value="${ it.inputYear }" />">年
						<input type="text" maxlength="2" size="2" name="month" id="month" value="<c:out value="${ it.inputMonth }" />">月
						<input type="text" maxlength="2" size="2" name="day" id="day" value="<c:out value="${ it.inputDay }" />">日
					</td>
				</tr>
				<tr>
					<td>FaceBook</td>
					<td>
					<input type="text" size="30" name="facebook" id="facebook" value="<c:out value="${ it.inputFacebook }" />">
					</td>
					<td>Twitter</td>
					<td>
					@<input type="text" size="30" name="twitter" id="twitter" value="<c:out value="${ it.inputTwitter }" />">
					</td>
				</tr>
				<tr>
					<td>感想</td>
					<td colspan="5">
						<textarea rows="6" cols="100" name="comment" id="comment"><c:out value="${ it.inputComment }" /></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="td_center">
					    <input type="submit" class="button" id="touroku" value="登録">
					</td>
				</tr>
				<tr>
					<td colspan="6" id="errors">
						<c:out value="${ it.message }" />
					</td>
				</tr>
			</table>
		</form>
	</div>
    <!-- 入力ここまで -->
	<div id="contents" class="contents_main_scroll">
			<table class="delay_table">
				<thead class="contents_main_title">
					<tr>
						<th class="hensyu"></th>
						<th class="hiduke">入力日付</th>
						<th class="namae">入力者名</th>
						<th class="kansou">感想</th>
					</tr>
				</thead>
				<tbody class="contents_main_body">
							<c:forEach var="bookComment" items="${it.book.userCommentList}">
								<tr>
									<td class="hensyu">
										<form method="get" action=<c:url value ="usercomment"></c:url>>
											<input type="submit" value="編集">
											<input type="hidden" name="commentId" value="<c:out value="${ bookComment.id}" />">
											<input type="hidden" name="bookId" value="<c:out value="${it.book.id}" />">
										</form>
									</td>
									<td class="hiduke"><c:out value="${ bookComment.date }" /></td>
									<td class="namae"><c:out value="${ bookComment.name }" /></td>
									<td class="kansou"><c:out value="${ bookComment.comment }" /></td>
								</tr>
							</c:forEach>
				</tbody>
			</table>
	</div>
	<!-- /contents_main_scroll -->

    <div id="footer">
		<div id="footer_bt_area">
			<div class="footer_box_left">

			</div>
			<div class="footer_box_right">

			</div>
			<p id="copyright">Copyright(c)2015 A'ワーク創造館 All rights Reserved</p>
		</div>
	</div>
	<!-- /footer -->

</div>
</body>
</html>