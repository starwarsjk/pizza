$( document ).ready(function() {
    console.log( "begin" );

    function addPizza(pizzaId, pizzaType) {
        $.ajax
        ({
            type: "POST",
            url: '/client/order/addPizza',
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify({"pizzaId": pizzaId, "pizzaType": pizzaType}),
            success: function () {
                alert("Pizza was added!");
            }
        });
    }

    function deletePizza(pizzaId, pizzaPrice) {
        $.ajax
        ({
            type: "POST",
            url: '/client/order/deletePizza',
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify({"pizzaId": pizzaId, "pizzaPrice": pizzaPrice}),
            success: function () {
                location.reload(true);
                // window.location.reload();
            }
        });
    }

    function deleteItem(itemId, itemPrice) {
        $.ajax
        ({
            type: "POST",
            url: '/client/order/deleteItem',
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify({"itemId": itemId, "itemPrice": itemPrice}),
            success: function () {
                location.reload(true);
                // window.location.reload();
            }
        });
    }

    $(".dodajPizze").click(function () {
        var pizzaId = $(this).attr("pizza-id");
        var pizzaType = $(this).attr("pizza-type");
        addPizza(pizzaId, pizzaType);
    });

    function addItem(itemId) {
        $.ajax
        ({
            type: "POST",
            url: '/client/order/addItem',
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify({"itemId": itemId}),
            success: function () {
                alert("Item was added!");
            }
        });
    }

    $(".addItem").click(function () {
        var itemId = $(this).attr("item-id");
        addItem(itemId);
    });

    function acceptOrder(orderId) {
        $.ajax
        ({
            type: "GET",
            url: '/user/order/accept/' + orderId,
            contentType:"application/json; charset=utf-8",
            // data: JSON.stringify({"pizzaId": pizzaId, "pizzaType": pizzaType}),
            success: function () {
                alert("Zamówienie jest w trakcie przygotowywania!");
            }
        });
    }

    function orderInTransport(orderId) {
        $.ajax
        ({
            type: "GET",
            url: '/user/order/orderInTransport/' + orderId,
            contentType:"application/json; charset=utf-8",
            // data: JSON.stringify({"pizzaId": pizzaId, "pizzaType": pizzaType}),
            success: function () {
                alert("Zamówienie jest właśnie dostarczane do klienta!");
            }
        });
    }

    function orderFinished(orderId) {
        $.ajax
        ({
            type: "POST",
            url: '/user/order/orderFinished/' + orderId,
            contentType:"application/json; charset=utf-8",
            // data: JSON.stringify({"pizzaId": pizzaId, "pizzaType": pizzaType}),
            success: function () {
                alert("Realizacja zamówienia została zakończona!");
            }
        });
    }

    $(".orderWaitForAccept").click(function () {
        var orderId = $(this).attr("order-id");
        acceptOrder(orderId);
        // location.reload();
        $(this).removeClass("orderWaitForAccept").addClass("orderIsInProgress").change();
        $(this).children(":first").text("IN_PROGRESS");
    });

    $(".orderIsInProgress").click(function () {
        var orderId = $(this).attr("order-id");
        orderInTransport(orderId);
        // location.reload();
        $(this).removeClass("orderIsInProgress").addClass("orderIsDuringDelivery").change();
        $(this).children(":first").text("DURING_DELIVERY");
    });

    $(".orderIsDuringDelivery").click(function () {
        var orderId = $(this).attr("order-id");
        orderFinished(orderId);
        // location.reload();
        $(this).removeClass("orderIsDuringDelivery").addClass("orderDone").change();
        $(this).children(":first").text("DONE");
    });


    $(".usunPizze").click(function () {
        var pizzaId = $(this).attr("pizza-id");
        var pizzaPrice = $(this).attr("pizza-price");
        deletePizza(pizzaId, pizzaPrice);
        // location.reload();
    });

    $(".usunItem").click(function () {
        var itemId = $(this).attr("item-id");
        var itemPrice = $(this).attr("item-price");
        deleteItem(itemId, itemPrice);
        // location.reload();
    });




    console.log( "end" );
});