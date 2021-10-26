accountEmployee();

var employeeAccoutnId;
var employeePssAcc;

//Function to retrieve employee account information for employee user
async function accountEmployee(){
	let res = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/employeeAccount');
	let data = await res.json();
	//let data1 = JSON.stringify(data);
	console.log(data);
	populateAccount(data);
}

//function to populate the account update fields
function populateAccount(data){
	//e.preventDefault();
		const keys = Object.keys(data);
		const length = keys.length;
//start at 1 to skip the userId key and length -2 to avoid las 2 key values
		for(let i = 1; i < length - 2; i++){
		    const key = keys[i]
		    document.getElementById(key).value = data[key];
		}
}
//Code above loads the fields for the account to be updated
//-----------------------------------------------------------------------------------------
//Code below to make the update request 

