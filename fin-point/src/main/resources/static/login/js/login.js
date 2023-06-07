document.getElementById('login')
    .addEventListener('submit', function (event) {
        event.preventDefault();

        const loginMemberDto = {
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        }

        fetch('/finpoint/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginMemberDto)
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = "/mailConfirm";
                } else {
                    alert("존재하지 않는 회원입니다.")
                }
            })
            .catch(error => {
                console.log("전송 오류")
            })
    })

document.getElementById('googleLogin').addEventListener('click', function () {
    fetch("/finpoint/oauth/google", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => response.text())
        .then(data => {
            window.open(data, '로그인', 'width=600,height=800, top=100, left=700, scrollbars=yes, resizable=yes')
        })
})

document.getElementById('naverLogin').addEventListener('click', function () {
    fetch("/finpoint/oauth/naver", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.text())
        .then(function (data) {
            window.open(data, "로그인", "width=800px,height=800px")
        })
})

document.getElementById('kakaoLogin').addEventListener('click', function () {
    fetch("/finpoint/oauth/kakao", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.text())
        .then(function (data) {
            window.open(data, "로그인", "width=500px,height=800px")
        })
})