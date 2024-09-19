document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('serchStudentForm');
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

        // 유효성 검사 실패 시 폼 제출 방지
        if (!isValid) {
            event.preventDefault(); // 폼 제출 방지
        }
    });
});
