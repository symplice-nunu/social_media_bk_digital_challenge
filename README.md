Social Media Spring Boot Application

Folder Structure
The project follows the following folder structure:

controllers

AuthController.java
CommentController.java
PostController.java

domain

Comment.java
Like.java
Post.java
Role.java
User.java

dtos

CommentDto.java
ErrorDetailsDto.java
JwtAuthResponseDto.java
LoginDto.java
PostPageResponseDto.java
RegisterDto.java
PostDto.java

repositories

CommentRepository.java
LikeRepository.java
PostRepository.java
RoleRepository.java
UserRepository.java

security

CustomUserDetailsService.java
JwtAuthenticationEntryPoint.java
JwtAuthenticationFilter.java
JwtTokenProvider.java

services

AuthService.java
CommentService.java
PostService.java
AuthServiceImpl.java
CommentServiceImpl.java
PostServiceImpl.java

MySQL Database
Database Name: socialmedia

Server Port: 1010


Postman Requests

User Authentication

Create New User

Endpoint: http://localhost:1010/api/auth/register
Method: POST
JSON Request:
{
"name" : "gaga",
"password": "gaga",
"email": "gaga@gmail.com",
"username" : "gatg"
}

Login

Endpoint: http://localhost:1010/api/auth/login
Method: POST
JSON Request:
{
"usernameOrEmail" : "admin",
"password": "admin"
}

Get All Posts

Endpoint: http://localhost:1010/api/posts/all
Method: GET

Update Post

Endpoint: http://localhost:1010/api/posts/cb46d347-e5d8-4f4f-a717-2dcce5e79203/update
Method: PUT
JSON Request:
{
"id": "cb46d347-e5d8-4f4f-a717-2dcce5e79203",
"title" : "Yves Post updated",
"description": "description updated",
"content": "content"
}

Create Post

Endpoint: http://localhost:1010/api/posts
Method: POST
JSON Request:
{
"title" : "Yves checking",
"description": "checking description",
"content": "yves@gmail.com"
}

Like Post

Endpoint: http://localhost:1010/api/posts/64b7652b-57f8-4a59-bb5f-90f0e47fc94d/like
Method: POST

Comment

Get All Comments

Endpoint: http://localhost:1010/api/posts/816fe6e9-dd46-4b89-9c22-0b3404c3ba86/comments
Method: GET

Create Comment

Endpoint: http://localhost:1010/api/posts/816fe6e9-dd46-4b89-9c22-0b3404c3ba86/comments
Method: POST
JSON Request:
{
"name": "comment name 3",
"email": "user@gmail.com",
"body" : "i like this post 3"
}

Delete Comment

Endpoint: http://localhost:1010/api/posts/816fe6e9-dd46-4b89-9c22-0b3404c3ba86/comments/102dc8c6-732a-40e4-85ad-614d51a88598/delete
Method: DELETE

Update Comment

Endpoint: http://localhost:1010/api/posts/816fe6e9-dd46-4b89-9c22-0b3404c3ba86/comments/96f090f1-bbee-4ace-87a9-963ca0150658/update
Method: PUT
JSON Request:
{
"id": "96f090f1-bbee-4ace-87a9-963ca0150658",
"name": "Yves N",
"email": "yves@gmail.com",
"body": "Comment is updated on post"
}
IDE Used
IntelliJ IDEA