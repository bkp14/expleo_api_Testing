const express = require("express");

const app = express();

app.use(express.json());

app.post("/login", (req, res) => {
    const { username, password } = req.body;

    if (username === "admin" && password === "admin123") {
        return res.json({
            token: "SDET_TOKEN_12345"
        });
    }

    return res.status(401).json({
        message: "Invalid Credentials"
    });
});

app.options("/products", (req, res) => {
    res.set("Allow", "GET, POST, PUT, DELETE, OPTIONS");
    res.status(200).send();
});

app.get("/products", (req, res) => {
    res.json([
        { id: 1, name: "Product 1" }
    ]);
});

app.listen(5000, () => {
    console.log("Auth Running");
});