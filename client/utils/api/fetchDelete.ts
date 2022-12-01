export async function fetchDelete(url: string, id: number) {
  const requestOptions = {
    method: "DELETE",
    headers: {
      Authorization: "Bearer my-token",
    },
  };

  try {
    const response = await fetch(`${url}${id}`, requestOptions);
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
  const requestOptions = {
    method: "PATCH",
    headers: {
      Authorization: "Bearer my-token",
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
  const requestOptions = {
    method: "PATCH",
    headers: {
      Authorization: "Bearer my-token",
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
