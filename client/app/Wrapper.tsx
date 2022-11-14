'use client';
import { unstable_getServerSession } from 'next-auth';
import React from 'react';
import SessionContainer from '../components/Providers/SessionProvider';

const Wrapper = async ({ children }: any) => {
	const session = await unstable_getServerSession();

	return <SessionContainer session={session}>{children}</SessionContainer>;
};

export default Wrapper;
