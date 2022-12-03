/** @type {import('next').NextConfig} */
const withPwa = require('next-pwa')({
	dest: 'public',
});

const settings = {
	reactStrictMode: true,
	swcMinify: true,
	experimental: { appDir: true },
	// fontLoaders is not a proper key
	// fontLoaders: [{ loader: '@next/font/google' }],
	images: {
		remotePatterns: [
			{
				protocol: 'https',
				hostname: '**',
			},
		],
	},
};
/*
const settings = {
	// BUG: Strict mode true makes video streaming network be severed during request.
	// reactStrictMode: true,
	reactStrictMode: false,
	swcMinify: true,
	experimental: { appDir: true },
	// fontLoaders is not a proper key
	// fontLoaders: [{ loader: '@next/font/google' }],
	images: {
		remotePatterns: [
			{
				protocol: 'https',
				hostname: '**',
			},
		],
	},
};
*/

// module.exports =
// 	process.env.NODE_ENV === 'development' ? settings : withPwa(settings);

module.exports = settings;

//picsum.photos/seed/picsum/200/300
