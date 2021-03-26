	const warning = $("#warningTxt");
	const id = $("#cuId");
	const pw = $("#cuPw");
	const pw2 = $("#cuPw2");
	const name = $("#cuName");
	const cell = $("#cuCellNum");
	
	const idReg = /^[a-zA-Z0-9]{4,20}$/
	const pwReg = /^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[$@!%*#?&])[A-Za-z0-9$@!%*#?&]{8,20}$/
	const nameReg = /^[a-zA-Z가-힣]{2,30}$/
	const cellReg = /^[0-9]{11,15}$/
	var isIdChecked = false;
	
	id.keydown(function()
	{
		isIdChecked = false;
		if(warning.text().length > 0 || $("#idError").text().length > 0)
		{
			warning.text(msgIdAgain)
			$("#idError").text("");
		}
	})
	
	function checkId()
	{
		var requestedId = {requestedId: id.val()};
	
		var idVaildation = check(idReg, id, msgIdPattern);
		if (idVaildation) 
		{
			$.ajax({
				url: '/check/id'
				,data: requestedId
				,method: 'post'
				,success: function(result)
				{
					if (result != 0)
					{
						warning.text(msgIdduplicated);
						isIdChecked = false;
					} 
					else 
					{
						warning.text(msgIdAvailable);
						isIdChecked = true;
					}
				}
				,error: function(e)
				{
					alert("error" + e);
				}
			});
		}
	}
	
	function check(re, target, message) 
	{
		if (target.val().length > 0) 
		{
			if (re.test(target.val()))
			{
				return true;
			}
			else 
			{
				alert(message);
				target.val("");
				target.focus();
				return false;
			}
		} 
		else 
		{
			alert(target.parent().siblings("th").children("span").text() + '를(을) 입력해주세요.');
		}
	}
	
	function defineTargets()
	{
		var valObj = {
			target: [id, pw, name],
			reg: [idReg, pwReg, nameReg],
			message: [msgIdPattern
				, msgPwPattern
				, msgNamePattern
				]
		};
	
		if (cell.val().length > 0) 
		{
			valObj.reg.push(cellReg);
			valObj.target.push(cell);
			valObj.message.push(msgCellPattern);
		}
		
		return valObj
	}
	
	function pwCheck()
	{
		if (pw2.val().length == 0) 
		{
			alert(msgPwConfirm);
			pw2.focus();
			return false;
		}
		else
		{
			if (pw.val() != pw2.val()) 
			{
				alert(msgPwConfirmError);
				pw2.val("");
				pw2.focus();
				return false;
			} 
			else 
			{
				return true;
			}
		}
		
	}
	
	function validation()
	{
		var valObj = defineTargets();
		
		for (var i = 0; i < valObj.target.length; i++) 
		{
			if (check(valObj.reg[i], valObj.target[i], valObj.message[i])) 
			{
				if($(valObj.target[i]).attr('id') == 'cuPw')
				{
					if(pwCheck()==false)
					{
						return false;
					}
				}
				continue;
			} 
			else 
			{
				return false;
			}
		}
		
		return !isIdChecked ? (alert(msgIdConfirm), false) : true
	}
	
	function handleSubmit() 
	{
		if (validation()) 
		{
			$("#joinForm").submit();
		}
	}