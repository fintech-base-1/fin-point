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