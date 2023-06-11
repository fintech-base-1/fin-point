let currentPage = 0;

window.addEventListener('scroll', function() {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        currentPage++;
        fetchMoreData();
    }
});

function fetchMoreData() {
    fetch(`/ranking?page=${currentPage}`)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Error: ' + response.status);
            }
            return response.text();
        })
        .then(parseData)
        .catch(error => console.error('Error:', error));
}

function parseData(data) {
    const html = new DOMParser().parseFromString(data, 'text/html');
    const newItems = html.querySelectorAll('ol[role="list"] li');
    const target = document.querySelector('ol[role="list"]');

    newItems.forEach(item => target.appendChild(item));
}