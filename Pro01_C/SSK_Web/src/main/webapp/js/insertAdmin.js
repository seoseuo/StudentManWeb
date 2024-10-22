document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('insertAdminForm');
    // 폼을 제출하기 전에 데이터 입력 유효성 검사를 하기 위함.

    form.addEventListener('submit', (event) => {

        // 유효성 검사 결과를 저장할 변수
        let isValid = true; // 기존 값 참, 예외 발생 시 거짓으로 처리하여 제출을 방지함.

        // 아이디 유효성 검사
        const id = form.elements['id'].value; // 입력한 Id 값 가져옴.

        const idWarn = document.getElementById('id_warn');
        if (id === '') {
            idWarn.textContent = '* 아이디를 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
            isValid = false;
        } else if (/\s/.test(id)) {
            idWarn.textContent = '* 아이디에 공백은 포함될 수 없습니다.'; // 2. 공백이 포함되어 있을 때
            isValid = false;
        } else if (/[^a-zA-Z0-9]/.test(id)) { // 3. 아이디에 영문자와 숫자 외의 문자가 포함될 때
            idWarn.textContent = '* 아이디는 영문자와 숫자만 입력 가능합니다.';
            isValid = false;
        } else { // 오류 없을 때
            idWarn.textContent = ''; // 경고 메시지 초기화
        }

        // 비밀번호 유효성 검사
        const password = form.elements['password'].value; // 입력한 password 값 가져옴.
        const passwordWarn = document.getElementById('pw_warn');
        if (password === '') {
            passwordWarn.textContent = '* 비밀번호를 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
            isValid = false;
        } else if (/\s/.test(password)) {
            passwordWarn.textContent = '* 비밀번호에 공백은 포함될 수 없습니다.'; // 2. 공백이 포함되어 있을 때
            isValid = false;
        } else {
            passwordWarn.textContent = ''; // 경고 메시지 초기화
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

        // 등록할 학생 수 유효성 검사
        const count = form.elements['count'].value; // 입력한 count 값 가져옴.
        const countWarn = document.getElementById('count_warn');
        if (count === '') {
            countWarn.textContent = '* 등록할 학생 수를 입력하세요.'; // 1. 아무 값도 입력하지 않았을 때
            isValid = false;
        } else if (!/^\d+$/.test(count) || parseInt(count, 10) <= 0) {
            countWarn.textContent = '* 등록 학생 수는 양의 정수여야 합니다.';
            isValid = false;
        }else if (/\s/.test(count)) {
            countWarn.textContent = '* 등록 학생 수에 공백은 포함될 수 없습니다.'; // 2. 공백이 포함되어 있을 때
            isValid = false;
        } else {
            countWarn.textContent = ''; // 경고 메시지 초기화
        }

        // 유효성 검사 실패 시 폼 제출 방지
        if (!isValid) {
            event.preventDefault(); // 폼 제출 방지
        }
    });
});
