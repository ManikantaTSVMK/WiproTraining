// function wait(time)
// {

//const { forEach } = require("async");

//     // creating a promise that resolves after the provided time in milliseconds
//     return new Promise (resolve => setTimeout(resolve, time));

//     // before calling resolve() it will wait for the milliseconds you have defined which signals that the promise is complete


// }

// // async function tell JS that this function will use Promise and use the await keyword
// async function run()
// {

//     //It looks like a synchronous call even 
//     console.log("Started ... ");
//     await wait(1000); // it will wait for a second and then resolves
//     console.log("After 1000 ms it is stopped");
// }
// // await will pause the function unitl the Promise resolves 

// run();

// API 
function fetchProducts()
{
  
    return new Promise(resolve =>
        { 
                setTimeout(() => { resolve(["laptop" , "Mouse" , "speakers"]);

                                 }, 2000); // 2 seconds of delay in process or loading
        });
}

async function displayProducts()
{

    console.log("Fetching the products from the backend");
    const products = await fetchProducts(); //wait for the result or api call
    const productList = document.getElementById("products");
    productList.innerHTML = products.map(product=>product);

}


displayProducts();

