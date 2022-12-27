import mongoose from 'mongoose';

const MONGODB_URI = process.env.DB_URL;

if (!MONGODB_URI) {
	throw new Error(
		'Please define the MONGODB_URI environment variable inside .env.local'
	);
}

/**
 * Global is used here to maintain a cached connection across hot reloads
 * in development. This prevents connections growing exponentially
 * during API Route usage.
 */
let cached = global.mongoose;

if (!cached) {
	cached = global.mongoose = { conn: null, promise: null };
}

async function dbConnect() {
	console.log('dbConnect');
	if (cached.conn) {
		return cached.conn;
	}
	console.log('cached con false');
	if (!cached.promise) {
		console.log('cached.promise false');
		const opts = {
			bufferCommands: false,
		};
		console.log('MONGODB_URI: ', MONGODB_URI);
		cached.promise = mongoose.connect(MONGODB_URI, opts).then((mongoose) => {
			console.log('mongoose connection SUCCESS');
			return mongoose;
		});
	}

	try {
		cached.conn = await cached.promise;
	} catch (e) {
		cached.promise = null;
		throw e;
	}

	return cached.conn;
}

export default dbConnect;
