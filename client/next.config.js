/** @type {import('next').NextConfig} */
const withPwa = require('next-pwa')({
	dest: 'public',
});

const settings = {
	reactStrictMode: true,
	swcMinify: true,
	experimental: { appDir: true },
	fontLoaders: [{ loader: '@next/font/google' }],
	images: {
		remotePatterns: [
			{
				protocol: 'https',
				hostname: '**',
			},
		],
	},
};

module.exports =
	process.env.NODE_ENV === 'development' ? settings : withPwa(settings);

//picsum.photos/seed/picsum/200/300
