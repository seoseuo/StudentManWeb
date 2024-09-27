document.addEventListener('DOMContentLoaded', () => {
	const form = document.getElementById('loginAdminForm');
	let focusSet = false; // 커서 이동을 한 번만 실행하도록 설정

	// 폼을 제출하기 전에 데이터 입력 유효성 검사를 하기 위함.
	form.addEventListener('submit', (event) => {

		// 유효성 검사 결과를 저장할 변수
		let isValid = true;

		// 아이디 유효성 검사
		const id = form.elements['id'].value; // 입력한 Id 값 가져옴.
		const idWarn = document.getElementById('id_warn');
		const idCursor = document.getElementById('id'); // 커서 이동을 위한 요소

		if (id === '') {
			idWarn.textContent = '* 아이디를 입력하세요.';
			isValid = false;
			if (!focusSet) {
				idCursor.focus();
				focusSet = true;
			}
		} else if (/\s/.test(id)) {
			idWarn.textContent = '* 아이디에 공백은 포함될 수 없습니다.';
			isValid = false;
			if (!focusSet) {
				idCursor.focus();
				focusSet = true;
			}
		} else if (/[^a-zA-Z0-9]/.test(id)) {
			idWarn.textContent = '* 아이디는 영문자와 숫자만 입력 가능합니다.';
			isValid = false;
			if (!focusSet) {
				idCursor.focus();
				focusSet = true;
			}
		} else {
			idWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 비밀번호 유효성 검사
		const password = form.elements['password'].value;
		const passwordWarn = document.getElementById('pw_warn');
		const passwordCursor = document.getElementById('password'); // 커서 이동을 위한 요소

		if (password === '') {
			passwordWarn.textContent = '* 비밀번호를 입력하세요.';
			isValid = false;
			if (!focusSet) {
				passwordCursor.focus();
				focusSet = true;
			}
		} else if (/\s/.test(password)) {
			passwordWarn.textContent = '* 비밀번호에 공백은 포함될 수 없습니다.';
			isValid = false;
			if (!focusSet) {
				passwordCursor.focus();
				focusSet = true;
			}
		} else {
			passwordWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 유효성 검사 실패 시 폼 제출 방지
		if (!isValid) {
			event.preventDefault(); // 폼 제출 방지
		}
	});
});
