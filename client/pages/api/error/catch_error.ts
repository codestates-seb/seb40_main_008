import { NextApiRequest, NextApiResponse } from 'next';
import { Client, GatewayIntentBits, TextChannel } from 'discord.js';
import dbConnect from '../../../lib/dbConnect';
import ErrorLog from '../../../models/Error-Schema';

const client = new Client({
	intents: [GatewayIntentBits.DirectMessages],
});

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	const conn = await dbConnect();
	const { error, statusCode } = JSON.parse(req.body);
	/**
	 * error:string = https://pioneroroom.com/auth/1/likes 좋아요 버튼
	 */
	const errorUrl = error.split(' ')[0];
	const errorComponent = error.split(' ').slice(1).join('');

	var timestamp = getCurrentTime(); //?

	const isLoggedIn = await client.login(process.env.DISCORD_APPLICATION_ID);
	client.on('ready', () => {
		client.channels.fetch('1032546542579634222').then((channel) => {
			if (!channel) return;
			const textChannel = channel as TextChannel;
			textChannel.send(
				'ERROR: ' + error + '\n' + 'STATUS_CODE: ' + statusCode
			);

			const dbLog = {
				url: errorUrl,
				statusCode,
				component: errorComponent,
				time: timestamp,
			};

			console.log(dbLog);
			ErrorLog.create(dbLog);
		});
	});

	res.status(200).json({ message: 'success' });
}

function getCurrentTime() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();
	var timestamp =
		year +
		'-' +
		month +
		'-' +
		day +
		'-' +
		hours +
		'-' +
		minutes +
		'-' +
		seconds; //?
	return timestamp;
}
// const { MongoClient, ServerApiVersion } = require('mongodb');
// const uri = process.env.DB_URL;
// const client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true, serverApi: ServerApiVersion.v1 });
// client.connect(err => {
//   const collection = client.db("test").collection("devices");
//   // perform actions on the collection object
//   client.close();
// });
