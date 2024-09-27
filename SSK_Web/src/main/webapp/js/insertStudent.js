document.addEventListener('DOMContentLoaded', () => {
	const form = document.getElementById('insertStudentForm');
	let focusSet = false; // 커서 이동을 한 번만 실행하도록 설정

	// 폼을 제출하기 전에 데이터 입력 유효성 검사를 하기 위함.
	form.addEventListener('submit', (event) => {

		// 유효성 검사 결과를 저장할 변수
		let isValid = true;

		// 학번 유효성 검사
		const num = form.elements['num'].value;
		const numWarn = document.getElementById('num_warn');
		const numCursor = document.getElementById("num"); // 커서 이동을 위한 요소
		if (num === '') {
			numWarn.textContent = '* 학번을 입력하세요.';
			isValid = false;
			if (!focusSet) {
				numCursor.focus();
				focusSet = true;
			}
		} else if (/\s/.test(num)) {
			numWarn.textContent = '* 학번에 공백은 포함될 수 없습니다.';
			isValid = false;
			if (!focusSet) {
				numCursor.focus();
				focusSet = true;
			}
		} else if (!/^20\d{6}$/.test(num)) {
			numWarn.textContent = '* 학번 양식은 20****** 입니다.';
			isValid = false;
			if (!focusSet) {
				numCursor.focus();
				focusSet = true;
			}
		} else {
			numWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 이름 유효성 검사
		const name = form.elements['name'].value;
		const nameWarn = document.getElementById('name_warn');
		const nameCursor = document.getElementById("name"); // 커서 이동을 위한 요소
		if (name === '') {
			nameWarn.textContent = '* 이름을 입력하세요.';
			isValid = false;
			if (!focusSet) {
				nameCursor.focus();
				focusSet = true;
			}
		} else if (/\s/.test(name)) {
			nameWarn.textContent = '* 이름에 공백은 포함될 수 없습니다.';
			isValid = false;
			if (!focusSet) {
				nameCursor.focus();
				focusSet = true;
			}
		} else if (!/^[가-힣]+$/.test(name)) {
			nameWarn.textContent = '* 이름은 올바른 한글만 입력 가능합니다.';
			isValid = false;
			if (!focusSet) {
				nameCursor.focus();
				focusSet = true;
			}
		} else {
			nameWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 전공 유효성 검사
		const major = form.elements['major'].value;
		const majorWarn = document.getElementById('major_warn');
		const majorCursor = document.getElementById("major"); // 커서 이동을 위한 요소
		if (major === '') {
			majorWarn.textContent = '* 전공을 입력하세요.';
			isValid = false;
			if (!focusSet) {
				majorCursor.focus();
				focusSet = true;
			}
		} else if (/\s/.test(major)) {
			majorWarn.textContent = '* 전공에 공백은 포함될 수 없습니다.';
			isValid = false;
			if (!focusSet) {
				majorCursor.focus();
				focusSet = true;
			}
		} else if (!/^[가-힣]+$/.test(major)) {
			majorWarn.textContent = '* 전공은 올바른 한글 단어만 입력 가능합니다.';
			isValid = false;
			if (!focusSet) {
				majorCursor.focus();
				focusSet = true;
			}
		} else if (!/^[가-힣]+학과$/.test(major)) {
			majorWarn.textContent = '* 전공 양식은 **학과 입니다.';
			isValid = false;
			if (!focusSet) {
				majorCursor.focus();
				focusSet = true;
			}
		} else {
			majorWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 전화번호 유효성 검사
		const phone = form.elements['phone'].value;
		const phoneWarn = document.getElementById('phone_warn');
		const phoneCursor = document.getElementById("phone"); // 커서 이동을 위한 요소
		if (phone === '') {
			phoneWarn.textContent = '* 전화번호를 입력하세요.';
			isValid = false;
			if (!focusSet) {
				phoneCursor.focus();
				focusSet = true;
			}
		} else if (!/^010-\d{4}-\d{4}$/.test(phone)) {
			phoneWarn.textContent = '* 전화번호 양식은 010-****-**** 입니다.';
			isValid = false;
			if (!focusSet) {
				phoneCursor.focus();
				focusSet = true;
			}
		} else {
			phoneWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 유효성 검사 실패 시 폼 제출 방지
		if (!isValid) {
			event.preventDefault(); // 폼 제출 방지
		}
	});
});
