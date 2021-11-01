checkSession()

function checkSession(){
	fetch('http://localhost:8080/ExpenseReimbursementSystem/api/checkSession').then(function(Response) {
		console.log(Response.status)
		if(Response.status === 401)
		{
			alert('Access denied, you are not logged in!');
			location.href = '../html/login.html';
		}
	})
}
//-------------------------------verifying session----------------------------------------------------------

const monthNames = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];
    const dateObj = new Date();
    const month = monthNames[dateObj.getMonth()];
    const day = String(dateObj.getDate()).padStart(2, '0');
    const year = dateObj.getFullYear();
    const output = month  + '\n'+ day  + ',' + year;

document.getElementById("date").innerHTML = `Date: ${output}`;

document.getElementById('newRequest').addEventListener('submit', NewReimbRequest);

async function NewReimbRequest(e){
	e.preventDefault();
	//alert('POST method');
	
	let amount = document.getElementById('amount').value;
	let description = document.getElementById('description').value;
	let rdbtn = document.getElementsByName('metod');
	let type;
              
            for(i = 0; i < rdbtn.length; i++) {
                if(rdbtn[i].checked)
                type = rdbtn[i].value;
            }
            
	let newReimb = {
		amount,
		description,
		type
	};
	
	try{
		let req = await fetch('http://localhost:8080/ExpenseReimbursementSystem/api/NewReimbursement', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
			body: JSON.stringify(newReimb)
		});
		
		let res = await req.json();
		if(res.transactionStatus === 'Successful'){
			alert(res.message);
			location.href = '../html/EmployeeDashboard.html';
		}else{
		alert(res.message);
		}
	}catch(e){
		console.log('Something went wrong');
	}
}


