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
      },
      backgroundImage: {
        model: "url('/model.png')"
      },
      boxShadow: {
        'default': '0 0 6.25rem .3rem #1E1E1E'
      }
    },
  },
  plugins: [],
}

