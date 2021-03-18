	const warning = $("#warningTxt");
	const id = $("#cuId");
	const pw = $("#cuPw");
	const pw2 = $("#cuPw2");
	const name = $("#cuName");
	const cell = $("#cuCellNum");
	
	const idReg = /^[a-zA-Z0-9]{4,20}$/
	const pwReg = /^(?=.+[0-9])(?=.+[A-Za-z])(?=.+[$@!%*#?&])[A-Za-z0-9$@!%*#?&]{8,20}$/
	const nameReg = /^[a-zA-Z가-힣]{2,30}$/
	const cellReg = /^[0-9]{11,15}$/
	var isIdChecked = false;
	
	id.change(function(){
		isIdChecked = false;
	})
	
	function checkId()
	{
		var requestedId = {requestedId: id.val()};
	
		var idVaildation = check(idReg, id, "아이디는 최소 4자리~최대 20자리, 영문, 숫자만 허용됩니다.");
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
						warning.text("중복된 아이디가 있습니다.");
						isIdChecked = false;
					} 
					else 
					{
						warning.text("사용가능한 아이디 입니다.");
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
	
	function validation()
	{
		var valObj = {
			target: [id, pw, name],
			reg: [idReg, pwReg, nameReg],
			message: ["아이디는 최소 4자리~최대 20자리, 영문, 숫자만 허용됩니다."
				, "비밀번호는 길이 최소 8자리 ~ 최대 20자리, 영문/숫자/특문 조합만 허용됩니다."
				, "이름은 한글 또는 영문만 최소 두 자부터 30자 까지 허용됩니다."
				]
		};
	
		if (cell.val().length > 0) 
		{
			valObj.reg.push(cellReg);
			valObj.target.push(cell);
			valObj.message.push("하이픈을 제외한 11~15자리 숫자만 허용됩니다.");
		}
		
		for (var i = 0; i < valObj.target.length; i++) 
		{
			if (check(valObj.reg[i], valObj.target[i], valObj.message[i])) 
			{
				continue;
			} 
			else 
			{
				return false;
			}
		}
		
		var pwCheck = false;
		if (pw2.val().length == 0) 
		{
			alert("비밀번호 확인을 입력해주세요.");
		}
		else
		{
			if (pw.val() != pw2.val()) 
			{
				alert("비밀번호 확인이 일치하지 않습니다.");
				pwCheck = false;
				pw2.val("");
				pw2.focus();
			} 
			else 
			{
				pwCheck = true;
			}
		}
		
		if(!isIdChecked)
		{
			alert("아이디 중복확인이 필요합니다.");
			return false;	
		}
		
		return isIdChecked, pwCheck;
	}
	
	function handleSubmit() 
	{
		if (validation()) 
		{
			$("#joinForm").submit();
		}
	}