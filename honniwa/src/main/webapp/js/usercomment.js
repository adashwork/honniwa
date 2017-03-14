/**
 * UpdateUserCommentJsp用のJS
 */
//送信チェック
jQuery(function($) {
	$("#frm").on("submit",function () {
    			
	$("#errors").empty();
    			
  	var errMsgs = [];
 	//入力内容のチェック   			
    	if($("#year").val().length==0){
    		errMsgs.push("感想日付(年)が未入力です。");
    	}
    	if($("#month").val().length==0){
    		errMsgs.push("感想日付(月)が未入力です。");
    	}			
    	if($("#day").val().length==0){
    		errMsgs.push("感想日付(日)が未入力です。");
    	}
    			
    	//エラーメッセージの数で送信処理、エラー表示の切り替え
    	if (errMsgs.length > 0) {
    		//発生したエラーメッセージを取得
    		for (var i = 0; i < errMsgs.length; i++) {
    		 // エラーメッセージをエラー表示ブロックに追加
    			var $errList = $("<li></li>").text(errMsgs[i]);
    			$("#errors").append($errList);
    		}
    		return false;
    	} else {
    		//送信処理
    		var confirmBtn = window.confirm("送信しますか?");
    		if (confirmBtn == false) {
    		 //送信前画面に戻す
    		 	return false;
    		 }
    	}
    			
	})
});