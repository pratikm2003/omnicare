// proxy.js
import express from "express";
import fetch from "node-fetch";
import cors from "cors";

const app = express();
app.use(cors());
app.use(express.json());

// 🔐 इथे तुझी OpenAI API key ठेव
const OPENAI_API_KEY = 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; 

app.post("/chat", async (req, res) => {
  try {
    const { message } = req.body;

    const response = await fetch("https://api.openai.com/v1/chat/completions", {
      method: "POST",
      headers: {
        "Authorization": `Bearer ${OPENAI_API_KEY}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        model: "gpt-4o-mini",
        messages: [
          {
            role: "system",
            content:
              "You are a professional banking support assistant. Reply clearly and helpfully.",
          },
          { role: "user", content: message },
        ],
      }),
    });

    const data = await response.json();
    res.json(data);
  } catch (err) {
    console.error("Proxy Error:", err);
    res.status(500).json({ error: "Server Error" });
  }
});

app.listen(5000, () =>
  console.log("✅ Proxy running on http://localhost:5000")
);
