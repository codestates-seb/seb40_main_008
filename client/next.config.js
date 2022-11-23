/** @type {import('next').NextConfig} */
const withPwa = require('next-pwa')({
	dest: 'public',
});

module.exports = withPwa({
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
});

//picsum.photos/seed/picsum/200/300
