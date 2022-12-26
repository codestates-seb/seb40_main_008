/**
 * 
 * @param input fetch url
 * @param init undefined if null 
 * @param occur component name
 */

const Discord = require("discord.js");
const client = new Discord.Client();

client.login("MTA1Njg4MDQ2NTkwODM0Njk2Mw.Gk31O2.NnN17mTKpXItDp0SpZRHAmgwEnXNh9skop-Uvc");
console.log(client);

export default async function catchfetch(input: RequestInfo, init?: RequestInit | undefined, occur?: string): Promise<Response> {
    const res = await fetch(input, init);
    try {
        if (!res.ok) {
            throw new Error(res.url + ' ' + occur);
        }
    } catch (err) {
        const error = err as any;
        fetch(`http://localhost:3000/api/error/catch_error`, {
            method: 'POST',
            body: JSON.stringify({
                error: error,
            }),
        });
        client.on("ready", () => {
            console.log(`${client.user.tag}에 로그인하였습니다!`);
        });
        client.on("message", (msg: { content: string; reply: (arg0: string) => void; }) => {
            if (msg.content === "핑") {
                msg.reply("퐁!");
            }
        });
    }
    finally {
        return res;
    }
}