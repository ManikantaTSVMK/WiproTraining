// current date time 
// calculation the difference  no of hours he worked , login time and logout time  , timer , digital clock 
const now = new Date();
console.log(now.toString());

const start  = new Date("2025-08-01T09:00:00Z");
const end = new Date("2025-08-01T17:30:00Z");

const diffMs = end -start; // milliseconds
const diffHours = diffMs/(1000*60*60);

console.log(`Congratulations you have worked : ${diffHours}`);



// Closure function -- Keep in a memory like how many times user has visited the website 
// shopping cart  -- how many items added in a cart , how many items removed from the cart, get total price 
function makeaCounter()
{
   let count =0;
    return function()   // closure function
   {
     count +=1;
     return count;

   }

}

const c = makeaCounter();
console.log(c());
console.log(c());
console.log(c());
console.log(c());

// DOM Object --- document ,  element , .innerText  , .appendChild() , .querySelector()

// todoList type of application


const btn = document.getElementById("btn");
btn.addEventListener("click", () =>{

    const output =  document.getElementById("output");
    output.innerText = "You have clicked on a Button at :" + new Date().toString();
});


// Todo list 
const num1 = parseInt(prompt("Enter first number")); // by default it takes the value is string form
const num2 = parseInt(prompt("Enter second number"));
//arrow function
const add = (a , b) => a + b;
const subtraction = (a,b) => a-b;
document.writeln(`The addition of ${num1} and ${num2} is: ${add(num1,num2)}`);
document.writeln(`The Subtraction of ${num1} and ${num2} is: ${subtraction(num1,num2)}`);

// create a todo list to add a task , remove a task , display a task using closure function
// and also display a timer at the top (in header section) and create one button as a calculator(+ , - ,*) based on user choice input call the arrow function and input you can take the input of two number  using text box
