// import { Noto_Sans_KR } from '@next/font/google';
import '../styles/globals.css';
import SessionContainer from '../components/Providers/SessionProvider';
import { getSession } from '../utils/helper/session';
import { cookies, headers } from 'next/headers';
import Header from './Header';

// const noto = Noto_Sans_KR({
// 	weight: '400',
// 	fallback: ['Roboto'],
// 	subsets: ['latin'],
// });

const RootLayout = async ({ children }: any) => {
	const { segment } = children.props.childProp;
	// const session = await getSession(headers().get('cookie') ?? '');
	const session = null;
	const nextCookies = cookies();
	return (
		<html>
			<Header />
			<body>
				{/* <SessionContainer session={session}>{children}</SessionContainer> */}
				{children}
			</body>
		</html>
	);
};

export default RootLayout;
