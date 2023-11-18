/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        GeistRegular: 'Geist-Regular',
        GeistBold: 'Geist-Bold',
        GeistBlack: 'Geist-Black',
      }
    },
  },
  plugins: [],
}

