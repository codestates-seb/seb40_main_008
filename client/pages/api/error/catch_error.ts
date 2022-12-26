import { NextApiRequest, NextApiResponse } from 'next';
import { Client, GatewayIntentBits, TextChannel } from 'discord.js';

const client = new Client({
	intents: [GatewayIntentBits.DirectMessages],
});

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	const isLoggedIn = await client.login(process.env.DISCORD_APPLICATION_ID);
	client.on('ready', () => {
		client.channels.fetch('1032546542579634222').then((channel) => {
			if (!channel) return;
			const textChannel = channel as TextChannel;
			const body = JSON.parse(req.body);
			textChannel.send(
				'ERROR: ' + body.error + '\n' + 'STATUS_CODE: ' + body.statusCode
			);
		});
	});

	res.status(200).json({ message: 'success' });
}
