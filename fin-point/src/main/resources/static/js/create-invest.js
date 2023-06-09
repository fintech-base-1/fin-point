const ButtonElement = document.querySelector('#Button');
const FileElement = document.querySelector('#File');

const getData = async () => {
    try{
        const formData = new FormData();
        formData.append("user", "Pyo");
        formData.append("content", "this is content");
        for(let i = 0; i < FileElement.files.length; i++){
            formData.append("files", FileElement.files[i]);
        }
        const url = 'http://localhost:8080/board';

        const response = await axios.post(url, formData);
        console.log(response)
    }
    catch (error){
        console.error(error);
    }
}

ButtonElement.addEventListener('click', getData);