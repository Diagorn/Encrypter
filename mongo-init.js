db.createUser(
    {
        user: "encrypter",
        pwd: "encrypter",
        roles: [
            {
                role: "readWrite",
                db: "encrypter"
            }
        ]
    }
);