<html>
<head></head>
<body>
<form method="POST" name="submitForm">

<div align="center">
            <table>
                <tr>
                    <td>User name</td>
                    <td><input type="text" name="name" /></td>
                </tr>
                <tr>
                    <td>User email</td>
                    <td><input type="text" name="email" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
            <div style="color: red">${error}</div>

        </div>
</form>

</body>
</html>
