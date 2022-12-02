import { getCookie } from "cookies-next";

export async function fetchDelete(url: string, id: number) {
  const token = getCookie("accessToken");
  const requestOptions = {
    method: "DELETE",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  try {
    const response = await fetch(`${url}${id}`, requestOptions);
    console.log("adsadsad", `${url}${id}`);
    return response.status;
  } catch (err) {
    console.error(err);
  }
}

export async function fetchEditChapter(
  thumbnail: string,
  chapterOrder: string,
  title: string,
  chapterId: number
) {
  const token = getCookie("accessToken");
  const requestOptions = {
    method: "PATCH",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ thumbnail, chapterOrder, title }),
  };
  try {
    const response = await fetch(
      `http://localhost:8080/auth/contents/chapter/${chapterId}`,
      requestOptions
    );
    return response.status;
  } catch (err) {
    console.error(err);
  }
}

export async function fetchEditClass(
  videoFile: File,
  title: string,
  docsFile: File,
  details: string,
  uploadClassid: number
) {
  const token = getCookie("accessToken");
  const requestOptions = {
    method: "PATCH",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ videoFile, title, docsFile, details }),
  };
  try {
    const response = await fetch(
      `http://localhost:8080/auth/contents/chapter/${uploadClassid}`,
      requestOptions
    );
    return response.status;
  } catch (err) {
    console.error(err);
  }
}
