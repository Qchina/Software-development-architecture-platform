from pydub import AudioSegment

def convert_audio(input_file, output_file):
    # 载入音频文件
    audio = AudioSegment.from_file(input_file)
    
    # 将音频转换为单通道 (Mono)
    audio = audio.set_channels(1)
    
    # 设置采样率为 16K Hz
    audio = audio.set_frame_rate(16000)
    
    # 转换为 WAV 格式
    audio.export(output_file, format="wav")
    print(f"转换完成，保存为: {output_file}")

# 使用示例
input_audio = "/home/wyh/Downloads/response.wav"  # 输入音频文件路径
output_audio = "/home/wyh/Projects/aiapp/DH_live/web_demo/static/common/response.wav"    # 输出音频文件路径
convert_audio(input_audio, output_audio)

