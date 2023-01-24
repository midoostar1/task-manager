

const client = filestack.init(APIKEY);






let profilePicLink = document.getElementById("p-img");


profilePicLink.addEventListener("click", profileImgClickHandler);


function profileImgClickHandler(){
    console.log("reached")
    let imglink = "test";
    const imgOptions = {
        accept: ["image/*"],
        onFileUploadFinished:file =>{
            imglink = file.url;
            imglink = imglink.slice(8);
            console.log("Image Link: "+imglink);
            postData(imglink).then(data => console.log(data));
            console.log("FileUpload file url:"+file.url);
            console.log("FileUpload file size:"+file.size);

            setTimeout(()=>{
                location.reload();
            },500);

        }
    };


    client.picker(imgOptions).open();
    console.log("file picker ran");
    // postData(imglink).then(data => console.log(data));
}

async function postData(imgLink){
    let url = window.location.origin;

    let returnUrl = window.location.pathname;
    console.log("Return url: "+returnUrl);


    // await fetch(url,{
    //     method: 'GET',
    //     cache: "reload",
    //     body:{data:imgLink}
    // });


    url = url+"/users/imageUpload/"+imgLink+returnUrl;
    await fetch(url);
    console.log("Full url: "+url);
    console.log("img JS ran")
}



