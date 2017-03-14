<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta content="text/css" http-equiv="content-style-type" />
  <meta content="text/javascript" http-equiv="content-script-type" />
  <title>オーナー感想入力 | 本庭（ほんには）｜まちライブラリー感想カード・メッセージサイト</title>
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/start/jquery-ui.css">
  <link rel="stylesheet" href="../../css/ownercomment.css" />
<!--   <link rel="stylesheet" href="../../css/customeredit.css" /> -->
  <link href="https://fonts.googleapis.com/earlyaccess/sawarabimincho.css" rel="stylesheet" />
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

  <c:url value="/honniwa/web/" var="honniwa" />
  <c:url value="/biblio/web/" var="biblio" />

  <script>
  jQuery(function ($) {
	  $("#ownerButton").on("click", function () {
	  		$("#frm").attr("action","<c:out value="${honniwa}"/>admin/ownerinfo");
		});

	  	$("#touroku").on("click", function () {
	  		$("#frm").attr("action","<c:out value="${honniwa}"/>admin/ownercomment?bookId=<c:out value="${ it.bookId }" />");
		});
  	}
  });
  </script>
</head>

<body id="top">
  <!-- wrapper start -->
  <div id="wrapper">
    <!-- ヘッダー部分 ここから  -->
    <div id="header">
      <p id="logo" class="wf-sawarabimincho"><a href="" class="w_txt"></a></p>
      <h1 id="title">オーナー感想入力</h1>
    </div>
    <!-- ヘッダー部分 ここまで -->
    <form id="frm" action="<c:out value="${honniwa}"/>admin/ownercomment?bookId=<c:out value="${ it.bookId }" />" method="post">
    <!-- パンくず -->
    <div class="pan">
    <ul id="topic-path" class="clearfix">

    </ul>
    </div>
    <!-- パンくず -->
    <!-- コンテンツ ここから -->
    <div id="contents">

      <div class="search_box">
      <ul class="search_book clearfix">
    		<li><c:out value="${ it.message }" /><input type="hidden" name="message" value="<c:out value="${ it.message }" />"></li>
  		</ul>
       <p class="search_date"><span>西暦</span><input type="text" maxlength="4" name="year" id="year" style="ime-mode: disabled;" value="<c:out value="${ it.year }" />"> <span>年</span>
          <input type="text" maxlength="2" name="month" id="month" style="ime-mode: disabled;" value="<c:out value="${ it.month }" />"> <span>月</span>
          <input type="text" maxlength="2" name="day" id="day" style="ime-mode: disabled;" value="<c:out value="${ it.day }" />"> <span>日</span>
       </p>
  <ul class="search_book clearfix">
    <li><span>書籍管理番号：</span><c:out value="${ it.bookId }" /><input type="hidden" name="bookId" value="<c:out value="${ it.bookId }" />"></li>
    <li><span>書籍名：</span><c:out value="${ it.title }" /><input type="hidden" name="title" value="<c:out value="${ it.title }" />"></li>
  </ul>

      </div>
<!-- 検索条件など -->
<!-- 編集部分 ここから -->
<div id="edit" class="search_main">
  <div class="inner clearfix">
  <div class="lt_box">
    <table class="table_edit">

      <tr>
        <td class="tbl_name">
        		<!--
        		<input type="submit" class="button"  id="ownerButton" value="オーナーID">
		 		 -->
		 		 <label for="ownerId">オーナーID</label>
		 </td>

        <td>：</td>
        <td class="tbl_input">
        	<input type="text" id="ownerId" name="ownerId" style="ime-mode: active;" value="<c:out value="${ it.ownerComment.ownerId }" />">
        </td>

      </tr>
      <tr>
        <td><label for="customername">名前<br>（ニックネーム）</label></td>
        <td>：</td>
        <td class="tbl_input"><input type="text" name="name" id="name" style="ime-mode: active;" value="<c:out value="${ it.ownerComment.name }" />"></td>
      </tr>
      <tr>
        <td><label for="twitter：">twitter</label></td>
        <td>：</td>
        <td>@<input type="text" name="twitter" id="twitter" style="ime-mode: active;" value="<c:out value="${ it.ownerComment.twitter }" />"></td>
      </tr>
      <tr>
        <td><label for="Facebook">Facebook</label></td>
        <td>：</td>
        <td><input type="text" name="facebook" id="facebook" style="ime-mode: disabled;" value="<c:out value="${ it.ownerComment.facebook }" />"></td>
      </tr>
      <tr>
        <td><label for="mailaddress">E-MAIL</label></td>
        <td>：</td>
        <td><input type="text" name="email" id="email" style="ime-mode: disabled;" value="<c:out value="${ it.ownerComment.email }" />"></td>
      </tr>


</table>
</div>
<div class="rt_box">
  <p class="thoughts">この本は、オーナーが：</p>
  <ul>
    <li><label for="office"><input type ="checkbox" name="recommend1" id="recommend1" value="1"><span>”楽しかった”本です。</span></label></li>
    <li><label for="contact0"><input type ="checkbox" name="recommend2" id="recommend2" value="1" ><span>”感動した”本です。</span></label></li>
    <li><label for="contact0"><input type ="checkbox" name="recommend3" id="recommend3" value="1" ><span>”勉強になった”本です。</span></label></li>
    <li><label for="contact0"><input type ="checkbox" name="recommend4" id="recommend4" value="1"><span>”気持ちが楽になった”本です。</span></label></li>
  </ul>
</div>
</div>
    <table class="table_edit">
      <tr>
        <td><label for="massege">オーナーからのメッセージ</label></td>
        <td>：</td>
        <td>
        	<textarea name="ownerComment" id="ownerComment" rows="3"><c:out value="${ it.ownerComment.comment }" /></textarea>
        </td>
      </tr>
    </table>
</div>

    <div class="btn_contact">
  <input type="hidden" id="hiddenid" name="hiddenid" value="">
  <input type="submit" class="button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="touroku" value="登録">
    </div>
<!-- 編集部分 ここまで -->
</div>
</form>
<!-- コンテンツ ここまで -->
<!-- フッタースペース ここから -->
<div id="footer" class="clearfix">
  <ul class="ft_menu clearfix">
    <!--<li><a class="button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" href="web/adminmenu " />スタッフメニュー</a></li>-->
    <li><a class="button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" href="${biblio}web/" />ログアウト</a></li>
  </ul>
<p id="copyright">Copyright(c)2017 A'ワーク創造館 All rights Reserved</p>
</div>
<!-- フッタースペース ここまで -->
</div>
<!-- wrapper end -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
</body>

</html>