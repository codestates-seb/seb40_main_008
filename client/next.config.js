const { truncate } = require('fs');

/** @type {import('next').NextConfig} */
const withPwa = require('next-pwa')({
	dest: 'public',
	fallbacks: {
		document: '/_offline',
	},
	swSrc: 'service-worker.js',
});

const settings = {
	reactStrictMode: true,
	swcMinify: true,
	experimental: { appDir: true },
	// fontLoaders is not a proper key
	// fontLoaders: [{ loader: '@next/font/google' }],
	images: {
		domains: ['k.kakaocdn.net', 'lh3.googleusercontent.com'],
		remotePatterns: [
			{
				protocol: 'https',
				hostname: '**',
			},
		],
	},
};

module.exports = withPwa(settings);
