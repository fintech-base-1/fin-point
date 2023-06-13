let currentPage = 1;
let standard = 'type';

const buttons = document.querySelectorAll('.standard');
buttons.forEach(botton => {
    botton.addEventListener('click', function() {
        standard = this.id;
        loadMemberPage();
    });
});

async function loadMemberPage() {
    try {
        const response = await fetch(`/ranking/data`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                standard: standard,
                page: currentPage
            })
        })
        const members = await response.json();
        displayMembers(members);
    } catch (error) {
        console.error('T_T : ', error);
    }
}

function createMemberElement(member) {
    const li = document.createElement('li');

    const img = document.createElement('img');

    // 이미지 데이터 형식 확인
    const mimeType = getImageMimeType(member.image);

    // 데이터 URI 생성
    const base64Data = encodeBase64(member.image);
    const dataURI = 'data:' + mimeType + ';base64,' + base64Data;

    img.src = dataURI;
    li.appendChild(img);

    const nicknameDiv = document.createElement('div');
    nicknameDiv.textContent = member.nickname;
    li.appendChild(nicknameDiv);

    const emailDiv = document.createElement('div');
    emailDiv.textContent = member.email;
    li.appendChild(emailDiv);

    const typeCountDiv = document.createElement('div');
    typeCountDiv.textContent = member.typeCount;
    li.appendChild(typeCountDiv);

    const pieceRetainCountDiv = document.createElement('div');
    pieceRetainCountDiv.textContent = member.pieceRetainCount;
    li.appendChild(pieceRetainCountDiv);

    const assetAmountDiv = document.createElement('div');
    assetAmountDiv.textContent = member.assetAmount;
    li.appendChild(assetAmountDiv);

    return li;
}

// 이미지 데이터 형식에 따라 MIME 타입 동적 결정
function getImageMimeType(imageData) {
    // imageData로부터 이미지 형식을 얻는 방법은 환경에 따라 다를 수 있습니다.
    // 예를 들어, 파일 확장자나 MIME 타입을 서버에서 가져올 수도 있습니다.

    // 여기에서는 간단히 파일 이름을 추출하여 확장자로 MIME 타입을 결정합니다.
    const fileName = 'image.png'; // imageData에서 파일 이름 추출 예시
    const extension = fileName.split('.').pop().toLowerCase();

    // MIME 타입 매핑
    const mimeTypes = {
        'png': 'image/png',
        'jpeg': 'image/jpeg',
        'jpg': 'image/jpeg',
        // 다른 이미지 형식에 대한 매핑을 추가할 수 있습니다.
    };

    return mimeTypes[extension] || 'application/octet-stream';
}

// Base64 인코딩 함수
function encodeBase64(imageData) {
    // 이미지 데이터를 Base64로 인코딩하는 로직은 환경에 따라 다를 수 있습니다.
    // 여기에서는 간단히 Base64 인코딩된 문자열을 반환합니다.
    const base64Data = 'Base64EncodedData'; // imageData를 Base64로 인코딩한 예시
    return base64Data;
}

function displayMembers(members) {
    const memberList = document.getElementById('memberList');

    members.forEach(member => {
        const memberElement = createMemberElement(member);
        memberList.appendChild(memberElement);
    });
}

window.addEventListener('scroll', function () {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        currentPage++;
        loadMemberPage();
    }
});
window.onload = loadMemberPage;
