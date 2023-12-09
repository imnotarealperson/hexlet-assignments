@import exercise.dto.users.UserPage
@param UserPage userPage

<html>
<head>
    <title>Хекслет</title>
</head>
<body>
    <div>
        <p>ID - ${userPage.getUser().getId()}</p>
        <p>First name - ${userPage.getUser().getFirstName()}</p>
        <p>Last name - ${userPage.getUser().getLastName()}</p>
        <p>Email - ${userPage.getUser().getEmail()}</p>
    </div>
</body>
</html>