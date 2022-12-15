import React from 'react';

interface HeaderProp {
	children?: React.ReactNode;
}

const Header = ({ children, ...props }: HeaderProp) => {
	return (
		<head>
			<meta name="application-name" content="Class4989" />
			<meta name="apple-mobile-web-app-capable" content="yes" />
			<meta name="apple-mobile-web-app-status-bar-style" content="default" />
			<meta name="apple-mobile-web-app-title" content="PWA App" />
			<meta name="mobile-web-app-capable" content="yes" />
			<meta name="msapplication-TileColor" content="#2B5797" />
			<meta name="msapplication-tap-highlight" content="no" />
			<meta name="theme-color" content="#000000" />

			<link rel="manifest" href="/manifest.json" />
			<link
				rel="mask-icon"
				href="/icons/android-chrome-192x192.png"
				color="#5bbad5"
			/>
			<link rel="shortcut icon" href="/favicon.ico" />
			<meta
				name="class4989:image"
				content="https://class4989.one/public/icons/android-chrome-192x192.png"
			/>
			<meta
				name="viewport"
				content="minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no, user-scalable=no, viewport-fit=cover"
			/>
			<title>CLASS4989</title>
			<meta
				name="description"
				content="개인이 강의를 사고 팔 수 있는 플랫폼"
			></meta>
			<meta
				property="og:title"
				content="아무나 개인적으로 강의를 사고 파세요 - Class4989"
			></meta>
			<meta
				property="og:description"
				content="잘 나가는 교육자들에게 강의를 사고, 자신이 있으면 팔아보세요!!"
			></meta>
			<meta property="og:url" content="https://class4989.one/"></meta>
			<meta property="og:type" content="website"></meta>
		</head>
	);
};

export default Header;
