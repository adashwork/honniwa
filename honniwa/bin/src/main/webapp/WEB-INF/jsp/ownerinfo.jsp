<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<meta content="text/css" http-equiv="content-style-type" />
	<meta content="text/javascript" http-equiv="content-script-type" />
	<title>オーナー情報検索 | 本庭（ほんには）｜まちライブラリー感想カード・メッセージサイト</title>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/start/jquery-ui.css">
	<link rel="stylesheet" href="../../css/ownercomment.css" />
<!--	<link rel="stylesheet" href="../../css/customeredit.css" /> -->
<!--	<link rel="stylesheet" href="css/bibliopost.css" /> -->
	<link href="https://fonts.googleapis.com/earlyaccess/sawarabimincho.css" rel="stylesheet" />

</head>

<body id="booklist">
	<!-- ラッパーここから -->
	<div id="wrapper">

		<!-- ヘッダー部分 ここから  -->
		<div id="header">
			<h1 id="title">検索画面</h1>
		</div>
		<!-- ヘッダー部分 ここまで -->
		<!-- パンくず -->
		<div class="pan">
			<ul id="topic-path" class="clearfix">
				<li><a href="">TOP</a></li>
				<li><a href="">書籍一覧</a></li>
				<li><a href="">書籍編集</a></li>
				<li><a href="">オーナー感想入力</a></li>
				<li>オーナー情報検索</li>
			</ul>
		</div>
		<!-- パンくず -->
		<!-- コンテンツここから -->
		<div id="contents">
			<!-- メッセージ表示 -->
			<!-- serch_box -->
			<form id="frm" action="/honniwa/web/admin/ownerinfo" method="get">
	<div id="edit" class="owner_main">

	<p class="owner_txt">ここからオーナーID 名前（ニックネーム） twitter Facebook Email を検索できます。</p>
	<div class="inner clearfix">

		<div class="lt_box">

			<table class="table_edit">

			<tr>
				<td><label for="ownerId">オーナーID</label></td>
				<td>：</td>
				<td class="tbl_input"><input type="text" name="ownerId" id="ownerId" style="ime-mode: active;" value=""></td>
			</tr>
			<tr>
				<td><label for="name">名前（ニックネーム）</label></td>
				<td>：</td>
				<td><input type="text" name="name" id="name" style="ime-mode: active;" value=""></td>
			</tr>
			</table>
		</div>
		<div class="rt_box">
			<table class="table_edit">
				<tr>
					<td><label for="twitter">twitter</label></td>
					<td>：</td>
					<td><input type="text" name="twitter" id="twitter" style="ime-mode: disabled;" value=""></td>
				</tr>
				<tr>
					<td><label for="facebook">Facebook</label></td>
					<td>：</td>
					<td><input type="text" name="facebook" id="facebook" style="ime-mode: disabled;" value=""></td>
				</tr>
				<tr>
					<td><label for="email">E-MAIL</label></td>
					<td>：</td>
					<td><input type="email" name="email" id="email" style="ime-mode: disabled;" value=""></td>
				</tr>

			</table>
			</div>

		</div>
	</div>
    <div class="btn_contact">
		<input type="hidden" id="hiddenid" name="hiddenid" value="">
		<input type="submit" class="button" id="editbutton" value="検索">
    </div>
    </form>
</div>
<!-- /serch_box -->

<!-- リスト部分 ここから -->
<div id="edit" class="owner_main clearfix">
<div id="list">
  <table id="listTable" class="owner_tbl">
    <thead>

      <tr style="background-color: #2191c0; color:#fff;">
        <th class="owner00"></th>
        <th class="owner01">オーナーID</th>
        <th class="owner02">名前<br>（ニックネーム）</th>
        <th class="owner03">Twitter</th>
        <th class="owner04">Facebook</th>
        <th class="owner05">Email</th>
       </tr>
      </thead>

      <tbody>
      <c:forEach var="owner" items="${it}">
       <tr>
       	<td class="owner00">
       		<form id="frm" action="/honniwa/web/admin/ownerinfo" method="post">
       		<input type="submit" class="button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="editbutton" value="選択">
	       			<input type="hidden" name="ownerId" value="<c:out value="${ owner.ownerId }" />">
	       	</form>
	       </td>
	       <td class="owner01"><c:out value="${ owner.ownerId }" /></td>
			<td class="owner02"><c:out value="${ owner.name }" /></td>
			<td class="owner03"><c:out value="${ owner.twitter }" /></td>
			<td class="owner04"><c:out value="${ owner.facebook }" /></td>
			<td class="owner05"><c:out value="${ owner.email }" /></td>
      	</tr>
      </c:forEach>
    </tbody>

  </table>
</div>
</div>
    <div class="btn_contact">
		<input type="hidden" id="hiddenid" name="hiddenid" value="">
		<input type="submit" class="button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="editbutton" value="登録">
    </div>

		<!-- /contents -->

<!-- フッタースペース ここから -->
<div id="footer" class="clearfix">
  <ul class="ft_menu clearfix">
    <!--<li><a class="button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" href="web/adminmenu " />スタッフメニュー</a></li>-->
    <li><a class="button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" href="web ">ログアウト</a></li>
  </ul>
<p id="copyright">Copyright(c)2017 A'ワーク創造館 All rights Reserved</p>
</div>
<!-- フッタースペース ここまで -->
</div>
<!-- wrapper end -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/biblio.js "></script>
</body>

</html>