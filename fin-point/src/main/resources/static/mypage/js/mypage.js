    function authorizationOpenBank() {
    fetch("/finpoint/bank/auth", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.text())
        .then(function (data) {
            window.open(data, "인증", "width=800px, height=800px");
        })
}
    function openProfileWindow() {
    window.open("/finpoint/mypage/profile", '프로필 이미지 변경', 'width=400,height=300');
}

    function confirmProfileImage() {
        const fileInput = document.getElementById('profile-input');
        if (fileInput.files && fileInput.files[0]) {
            const formData = new FormData();
            formData.append('profileImage', fileInput.files[0]);

            fetch('/finpoint/mypage/update-profile', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    alert(data.message)
                    window.close()
                })
                .catch(error => {
                    console.error('이미지 업데이트 실패:', error);
                });
        } else {
            console.log('이미지를 선택하세요');
        }
    }

    function toggleSection(sectionId, element) {
        const sections = document.querySelectorAll('section[id^="piece-section-"]');
        const clickedSection = document.getElementById('piece-section-' + sectionId);
        sections.forEach(section => {
            if (section === clickedSection) {
                section.style.display = section.style.display === 'none' ? 'block' : 'none';
            } else {
                section.style.display = 'none';
            }
        })
        const paragraphs = document.querySelectorAll('p[id^="piece-"]');
        paragraphs.forEach(paragraph => {
            if (paragraph === element) {
                paragraph.classList.add('selected');
            } else {
                paragraph.classList.remove('selected');
            }
        })
    }

    const imageElement = document.getElementById('image');
    imageElement.onerror = function() {
        this.src = '/images/default.jpg';
    };

    function openResetGoalWindow() {
        window.open("/finpoint/mypage/reset-goal", '목표금액을 재설정 해주세요', 'width=400,height=300');
    }

    function resetGoal() {
        const goal = document.getElementById('goal').value;

        fetch('/finpoint/mypage/goal', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ goal: goal })
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message)
                window.close()
            })
            .catch((error) => {
                console.error('아오:', error);
            });
    }
