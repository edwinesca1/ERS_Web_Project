getPosts();

let container = document.getElementById('post-container');

async function getPosts(){
	let res = await fetch('http://localhost:8080/SocialHubWeekFour/api/posts');
	let data = await res.json();
	populatePosts(data);
}

function populatePosts(data){
	for(postObj of data){
		let post = document.createElement('div');
		post.innerHTML = `<h2>${postObj.username}</h2>
						  <p>${postObj.content}</p>`;
		container.append(post);
	}
}

document.getElementById("new-post-btn").addEventListener('click', newPost);

async function newPost(e){
	
	e.preventDefault();
	
	let req = await fetch('http://localhost:8080/SocialHubWeekFour/api/session');
	let res = await req.json();
	
	let id = res.userId;
	let content = document.getElementById("post-content").value;
	
	let post = {
		userId: id,
		wallId: id,
		content
	};
	
	console.log(post);
	
	req = await fetch('http://localhost:8080/SocialHubWeekFour/api/posts', {
		method: 'POST',
		contentType: "application/json",
		body: JSON.stringify(post)
	});
	
	container.innerHTML = '';
	content.value = '';
	
	await getPosts();
	
}