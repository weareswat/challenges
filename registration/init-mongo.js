db.createUser({
    user: "bahamasinvoice",
    pwd: "bahamasinvoice",
    roles : [{
        role: "readWrite",
        db: "bahamas"
    }]
})