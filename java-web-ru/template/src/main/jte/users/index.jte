@import exercise.dto.users.UsersPage
@param UsersPage usersPage

<html>
<head>
    <title>Хекслет</title>
</head>
<body>
@if(usersPage.getUsers().isEmpty())
    <p>Пока не добавлено ни одного пользователя</p>
@else
    @for(var user : usersPage.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()} </a></h2>
        </div>
    @endfor
@endif
</body>
</html>