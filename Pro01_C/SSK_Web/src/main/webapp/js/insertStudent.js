document.addEventListener('DOMContentLoaded', () => {
	const form = document.getElementById('insertStudentForm');
	// 폼을 제출하기 전에 데이터 입력 유효성 검사를 하기 위함.

	form.addEventListener('submit', (event) => {

		// 유효성 검사 결과를 저장할 변수
		let isValid = true; // 기존 값 참, 예외 발생 시 거짓으로 처리하여 제출을 방지함.

		// 학번 유효성 검사
		const num = form.elements['num'].value; // 입력한 num 값 가져옴.
		const numWarn = document.getElementById('num_warn');
		if (num === '') {
			numWarn.textContent = '* 학번을 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
			isValid = false;
		} else if (/\s/.test(num)) {
			numWarn.textContent = '* 학번에 공백은 포함될 수 없습니다.'; // 2. 공백이 포함되어 있을 때
			isValid = false;
		} else if (!/^20\d{6}$/.test(num)) { // 4. 20으로 시작하는 8자리 숫자가 아닌 경우
			numWarn.textContent = '* 학번 양식은 20****** 입니다.';
			isValid = false;
		} else {
			numWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 이름 유효성 검사
		const name = form.elements['name'].value; // 입력한 name 값 가져옴.
		const nameWarn = document.getElementById('name_warn');
		if (name === '') {
			nameWarn.textContent = '* 이름을 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
			isValid = false;
		} else if (/\s/.test(name)) {
			nameWarn.textContent = '* 이름에 공백은 포함될 수 없습니다.'; // 2. 공백이 포함되어 있을 때
			isValid = false;
		} else if (!/^[가-힣]+$/.test(name)) {
			nameWarn.textContent = '* 이름은 올바른 한글만 입력 가능합니다.'; // 3. 이름에 영어 또는 다른 문자가 포함될 때
			isValid = false;
		} else {
			nameWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 전공 유효성 검사
		const major = form.elements['major'].value; // 입력한 major 값 가져옴.
		const majorWarn = document.getElementById('major_warn');
		if (major === '') {
			majorWarn.textContent = '* 전공을 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
			isValid = false;
		} else if (/\s/.test(major)) {
			majorWarn.textContent = '* 전공에 공백은 포함될 수 없습니다.'; // 2. 공백이 포함되어 있을 때
			isValid = false;
		} else if (!/^[가-힣]+$/.test(major)) {
			majorWarn.textContent = '* 전공은 올바른 한글 단어만 입력 가능합니다.'; // 3. 전공에 영어 또는 다른 문자가 포함될 때
			isValid = false;
		} else if (!/^[가-힣]+학과$/.test(major)) {
			majorWarn.textContent = '* 전공은 양식은 **학과 입니다.'; // 전공이 한글로만 이루어져야 하고 "학과"로 끝나야 하는 경우
			isValid = false;
		} else {
			majorWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 전화번호 유효성 검사
		const phone = form.elements['phone'].value; // 입력한 phone 값 가져옴.
		const phoneWarn = document.getElementById('phone_warn');
		if (phone === '') {
			phoneWarn.textContent = '* 전화번호를 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
			isValid = false;
		} else if (!/^010-\d{4}-\d{4}$/.test(phone)) {
			phoneWarn.textContent = '* 전화번호 양식은 010-****-**** 입니다.'; // 2. 전화번호 정규식에 틀렸을 때
			isValid = false;
		} else {
			phoneWarn.textContent = ''; // 경고 메시지 초기화
		}

		// 유효성 검사 실패 시 폼 제출 방지
		if (!isValid) {
			event.preventDefault(); // 폼 제출 방지
		}
	});
});