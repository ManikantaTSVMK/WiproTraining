document.getElementById("submit").addEventListener("click" , function()
{
    // using some test api(Json data)
    fetch("https://jsonplaceholder.typicode.com/users/1")
    .then(response => {
   
        if(!response.ok)
        {
            throw new Error("Some server error !");
        }
       // console.log(response.json());
        return response.json();
         })
    .then(data => {

       
        document.getElementById("output").innerHTML = `<h3> Post Title:</h3> ${data.username}
                                                       <h3> Post Body :</h3> ${data.email}`;
        

    })
    .catch(error => {document.getElementById("output").innerHTML = "Error: " + error.message;
    
    });
    
});
