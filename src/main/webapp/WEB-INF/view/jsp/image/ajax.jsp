<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Spring MVC - Ajax</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
        body { background-color: #eee; font: helvetica; }
        #container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
        .green { font-weight: bold; color: green; }
        .message { margin-bottom: 10px; }
        label { width:70px; display:inline-block;}
        .hide { display: none; }
        .error { color: red; font-size: 0.8em; }
    </style>
</head>
<body>

<div id="container">

    <h1>test Image over Ajax</h1>
    <p>This page demonstrates Spring MVC's powerful Ajax functionality. 
    </p>

    <h2>Get By ID</h2>
    <form id="idForm">
        <div class="error hide" id="idError">Please enter a valid ID in range 0-1</div>
        <label for="imageId">ID (0-1): </label><input name="id" id="imageId" value="0" type="number" />
        <input type="submit" value="Get Image By ID" /> <br /><br/>
        <div id="imageIdResponse"> </div>
    </form>

    <hr/>

    <h2>Submit new Image</h2>
    <form id="newImageForm">
        <label for="pathInput">Path: </label>
        <input type="text" name="path" id="pathInput" />
        <br/>

        <label for="imageInput">Image: </label>
        <input type="text" name="age" id="imageInput" />
        <br/>
        <input type="submit" value="Save Image" /><br/><br/>
        <div id="imageFormResponse" class="green"> </div>
    </form>
</div>


<script type="text/javascript">

    $(document).ready(function() {

        // Request Image by ID AJAX
        $('#idForm').submit(function(e) {
            var imageId = +$('#imageId').val();
            if(!validateImageId(imageId))
                return false;
            $.get('${pageContext.request.contextPath}/image/login8/' + imageId, function(image) {
                $('#imageIdResponse').text(image.path);
            });
            e.preventDefault(); // prevent actual form submit
        });

        $('#newImageForm').submit(function(e) {
            // will pass the form date using the jQuery serialize function
            $.post('${pageContext.request.contextPath}/image/login8', $(this).serialize(), function(response) {
                $('#imageFormResponse').text(response);
            });

            e.preventDefault(); // prevent actual form submit and page reload
        });

    });

    function validateImageId(imageId) {
        console.log(imageId);
        if(imageId === undefined || imageId < 0 || imageId > 1) {
            $('#idError').show();
            return false;
        }
        else {
            $('#idError').hide();
            return true;
        }
    }


</script>

</body>
</html>