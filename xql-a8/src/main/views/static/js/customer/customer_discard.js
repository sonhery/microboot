$(function() {
	$("#customerForm").bootstrapValidator({
		live : 'enabled',
		excluded : [ ':disabled', ':hidden', ':not(:visible)' ],
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			discardId : {
				validators : {
					notEmpty : {
						message : "废弃客户编码不能为空"
					}
				}
			},
			remainId : {
				validators : {
					notEmpty : {
						message : "保留客户编码不能为空"
					}
				}
			}
		}
	});

	$("#btnSubmit").on("click", submitForm);

	function submitForm(obj) {
		var $form = $("form");
		$form.data("bootstrapValidator").validate(); // 手动验证
		var isValid = $form.data("bootstrapValidator").isValid(); // 是否验证成功
		if (isValid) {
			var r = confirm("是否确认提交废弃客户操作，此操作不可逆，请谨慎？");
			if (r) {
				var url = $form.attr("action");
				console.log($form.serialize());
				$.ajax({
					url : url,
					type : "post",
					async : true, // 是否异步请求（此处需这只为异步请求true，否则bootstrap的modal不会顺序显示）
					cache : false, // 是否缓存此页面，每次都请求服务器
//					contentType : "application/json", // 内容编码类型
					dataType : "json", // 预期服务器返回数据格式
					timeout : 60000, // 超时时间，60s
					data : $form.serialize(), // 请求参数
					error : function(xhr, errMsg, e) {
						alert("内部错误");
					},
					success : function(data) {
						alert(data.msg);
						if (data.code == 1) {
							$form.reset();
							alert(data.msg);
						}
					}, // 请求服务器成功的处理
				});
			}
		}
	}

});