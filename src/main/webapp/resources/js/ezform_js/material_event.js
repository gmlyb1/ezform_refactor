/**
 * 	공지사항 이벤트처리
 
 */
 
	
	$(document).ready(function() {
		$("#ma_file").on('change', function() {
			
			var maxSize = 50 * 1024 * 1024; // 50MB
			// 파일 용량 체크 (50MB 이하)
			var fileSize = $("#ma_file")[0].files[0].size;
			
			if(fileSize > maxSize){
				alert("첨부파일 사이즈는 50MB 이내로 등록 가능합니다.");
				$("#ma_file").val("");
				return false;
			}
		});
		
		
		//form태그 정보 가져오기
			var fr = $("form[role='form']");
		
		
			//수정하기
			$("#material_modify").click(function(){			
				
				fr.attr("action","/ezform/ez_material/modify");
				
				fr.attr("method","get");
				
				fr.submit();
			});
			
			//삭제하기
			$("#material_remove").click(function(){
				
				if (!confirm("삭제 하시겠습니까?")) return false;
				else {
					fr.attr("action","/ezform/ez_material/delete");
					fr.submit();
				}
			});
		
			//목록으로
			$("#material_list").click(function(){
				location.href = "/ezform/ez_material/list";
			});
	});
	
	
	// 공지사항 유효성 검사
	function materialWriteCk() {
		
		if($("#ma_title").val() == "") {
			alert("제목을 입력해주세요");
			return false;
		}
		
		if($("#ma_content").val() == "") {
			alert("내용을 입력해주세요");
			return false;
		}
		

	}