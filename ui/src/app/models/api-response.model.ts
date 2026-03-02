export interface ApiResponse<T> {
    status: {
      statusCode: number;
      message: string;
    };
    responseData: T;
  }